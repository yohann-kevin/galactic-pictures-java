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
public class GalacticPictureController {
    private GalacticPicturesService galacticPicturesService;

    public GalacticPictureController(GalacticPicturesService galacticPicturesService) {
        this.galacticPicturesService = galacticPicturesService;
    }

    @PostMapping("/picture/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGalacticPicture(@RequestParam String name, String description, String date) {
        this.galacticPicturesService.add(name, description, date);
    }

    @GetMapping("/picture/all")
    public Set<GalacticPictures> displayAllPicture() {
        Set<GalacticPictures> galacticPicturesList = this.galacticPicturesService.findAll();
        return galacticPicturesList;
    }

    @GetMapping("/picture/by")
    public GalacticPictures displayPictureDate(@RequestParam(value = "date", defaultValue = "2021-08-31") String date) {
        GalacticPictures galacticPicture = this.galacticPicturesService.findByDate(date);
        return galacticPicture;
    }

    @DeleteMapping("/picture/delete")
    public void deletePicture(@RequestParam(value = "id") String id) {
        this.galacticPicturesService.deleteById(id);
    }

    @GetMapping("/picture/description")
    public Map<String, String> displayDescription(@RequestParam(value = "id") String id) {
        Map<String, String> description = new HashMap<>();
        description.put("description", this.galacticPicturesService.seeDescription(id));
        return description;
    }

    @PutMapping("/picture/modify")
    public void modifyPicture(@RequestParam String id, String name, String description, String date) {
        this.galacticPicturesService.updatePicture(id, name, description, date);
    }

    @GetMapping("/find")
    public Map findDataToNasaApi() {
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";
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

        this.galacticPicturesService.add(data.get("title"), data.get("description"), data.get("date"));
        return res;
    }

    public Map formatNasaData(JsonNode node) {
        Map<String, String> data = new HashMap<>();
        data.put("date", node.get("date").asText());
        data.put("title", node.get("title").asText());
        data.put("description", node.get("explanation").asText());
        data.put("url", node.get("url").asText());
        data.put("hdurl", node.get("hdurl").asText());
        data.put("copyright", node.get("copyright").asText());
        data.put("mediaType", node.get("media_type").asText());
        return data;
    }
}
