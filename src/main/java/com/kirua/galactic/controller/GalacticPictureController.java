package com.kirua.galactic.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.service.GalacticPicturesService;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/picture")
public class GalacticPictureController {
    private GalacticPicturesService galacticPicturesService;

    public GalacticPictureController(GalacticPicturesService galacticPicturesService) {
        this.galacticPicturesService = galacticPicturesService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addGalacticPicture(@RequestParam String name, String description, String date, String url, String hdurl, String copyright, String mediaType) {
        this.galacticPicturesService.add(name, description, date, url, hdurl, copyright, mediaType);
    }

    @GetMapping
    public Set<GalacticPictures> displayAllPicture() {
        Set<GalacticPictures> galacticPicturesList = this.galacticPicturesService.findAll();
        return galacticPicturesList;
    }

    @GetMapping("/{date}")
    public GalacticPictures displayPictureDate(@PathVariable String date) {
        GalacticPictures galacticPicture = this.galacticPicturesService.findByDate(date);
        return galacticPicture;
    }

    @DeleteMapping
    public void deletePicture(@RequestParam(value = "id") String id) {
        this.galacticPicturesService.deleteById(id);
    }

    @GetMapping("/description/{uuid}")
    public Map<String, String> displayDescription(@PathVariable String uuid) {
        Map<String, String> description = new HashMap<>();
        description.put("description", this.galacticPicturesService.seeDescription(uuid));
        return description;
    }

    @PutMapping
    public void modifyPicture(@RequestParam String id, String name, String description, String date) {
        this.galacticPicturesService.updatePicture(id, name, description, date);
    }

    @GetMapping("/find")
    public Map findDataToNasaApi(String date) {
        String url = "https://api.nasa.gov/planetary/apod?api_key=NdLqdh0xJbsHOWvyYYymFKGQbGG8OMPoESNw2ZFh&date=" + date;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> res = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            String response = restTemplate.getForObject(url, String.class);
            String result = new JSONObject(response).toString();
            JsonNode node = mapper.readTree(result);
            data = this.formatNasaData(node);
            res.put("response", true);
            res.put("data", data);
        } catch (JsonProcessingException e) {
            res.put("response", false);
            res.put("error", e);
            e.printStackTrace();
        }

        this.galacticPicturesService.add(
                data.get("title"),
                data.get("description"),
                data.get("date"),
                data.get("url"),
                data.get("hdurl"),
                data.get("copyright"),
                data.get("mediaType")
        );
        return res;
    }

    public Map formatNasaData(JsonNode node) {
        Map<String, String> data = new HashMap<>();
        data.put("date", node.get("date").asText());
        data.put("title", node.get("title").asText());
        data.put("description", node.get("explanation").asText());
        data.put("url", node.get("url").asText());
        data.put("hdurl", node.get("hdurl").asText());
        if (node.get("copyright") != null) data.put("copyright", node.get("copyright").asText());
        data.put("copyright", this.manageCopyright(node));
        data.put("mediaType", node.get("media_type").asText());
        return data;
    }

    public String manageCopyright(JsonNode node) {
        if (node.get("copyright") == null) {
            return "This content has been provided by NASA";
        } else {
            return  node.get("copyright").asText();
        }
    }
}
