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

import java.text.SimpleDateFormat;
import java.util.*;

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
        this.galacticPicturesService.add(date, description, name, mediaType, copyright, hdurl, url);
    }

    @GetMapping
    public ArrayList<GalacticPictures> displayAllPicture() {
        ArrayList<GalacticPictures> galacticPicturesList = this.galacticPicturesService.findAll();
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
    public void findDataFromNasaApi() {
        Date currentDate = new Date();
        for (int i = 0; i > -5; i--) {
            Date beforeToday = this.addDays(currentDate, i);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            this.checkIfPictureIsAlreadyExist(dateFormat.format(beforeToday));
        }
    }

    public Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public void checkIfPictureIsAlreadyExist(String date) {
        if (this.displayPictureDate(date) == null) {
            this.getDataFromNasaApi(date);
        } else {
            System.out.println("error");
        }
    }

    public Map getDataFromNasaApi(String date) {
        Map<String, Object> res = new HashMap<>();
        String url = "https://api.nasa.gov/planetary/apod?api_key=NdLqdh0xJbsHOWvyYYymFKGQbGG8OMPoESNw2ZFh&date=" + date;
        RestTemplate restTemplate = new RestTemplate();
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
                data.get("date"),
                data.get("description"),
                data.get("title"),
                data.get("mediaType"),
                data.get("copyright"),
                data.get("hdurl"),
                data.get("url")
        );


        return res;
    }

    public Map formatNasaData(JsonNode node) {
        Map<String, String> data = new HashMap<>();
        data.put("date", node.get("date").asText());
        data.put("title", node.get("title").asText());
        data.put("description", node.get("explanation").asText());
        data.put("url", node.get("url").asText());
        if (node.get("hdurl") != null) data.put("hdurl", node.get("hdurl").asText());
        data.put("copyright", this.manageCopyright(node));
        data.put("mediaType", node.get("media_type").asText());
        return data;
    }

    public String manageCopyright(JsonNode node) {
        if (node.get("copyright") == null) {
            return "This content has been provided by NASA";
        } else {
            return node.get("copyright").asText();
        }
    }
}
