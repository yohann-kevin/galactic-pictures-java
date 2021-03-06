package com.kirua.galactic.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.domain.user.User;
import com.kirua.galactic.exception.InvalidUuidException;
import com.kirua.galactic.exception.PictureNotFoundException;
import com.kirua.galactic.service.FavoriteService;
import com.kirua.galactic.service.GalacticPicturesService;

import com.kirua.galactic.service.UserService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/picture")
@EnableSwagger2
public class GalacticPictureController {
    private GalacticPicturesService galacticPicturesService;

    private FavoriteService favoriteService;
    private UserService userService;

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

    @GetMapping("/date/{date}")
    public GalacticPictures displayPictureDate(@PathVariable String date) throws PictureNotFoundException {
        GalacticPictures galacticPicture = this.galacticPicturesService.findByDate(date);
        return galacticPicture;
    }

    @GetMapping("/id/{uuid}")
    public GalacticPictures displayPictureById(@PathVariable String uuid) throws PictureNotFoundException {
        GalacticPictures galacticPictures = this.galacticPicturesService.findById(uuid);
        return galacticPictures;
    }

    @DeleteMapping
    public void deletePicture(@RequestParam(value = "id") String id) throws PictureNotFoundException {
        this.galacticPicturesService.deleteById(id);
    }

    @PutMapping
    public void modifyPicture(@RequestParam String id, String date, String description, String title, String mediaType, String copyright, String hdurl, String url) throws PictureNotFoundException {
        this.galacticPicturesService.updatePicture(id, date, description, title, mediaType, copyright, hdurl, url);
    }

    @PostMapping("/like/{uuid}")
    @ResponseStatus(HttpStatus.CREATED)
    public void likePicture(@PathVariable String uuid, Principal principal) throws InvalidUuidException, PictureNotFoundException {
        this.galacticPicturesService.likePicture(uuid);
        GalacticPictures galacticPictures = this.galacticPicturesService.findById(uuid);
        User currentUser = this.userService.getUserByName(principal.getName());
        this.favoriteService.add(currentUser, galacticPictures);
    }

    @PostMapping("/download/{uuid}")
    @ResponseStatus(HttpStatus.CREATED)
    public void downloadPicture(@PathVariable String uuid) throws InvalidUuidException {
        this.galacticPicturesService.donwloadPicture(uuid);
    }

    @GetMapping("/find")
    public RedirectView findDataFromNasaApi() throws PictureNotFoundException {
        Date currentDate = new Date();
        for (int i = 0; i > -30; i--) {
            Date beforeToday = this.addDays(currentDate, i);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            this.checkIfPictureIsAlreadyExist(dateFormat.format(beforeToday));
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/api/picture");
        return redirectView;
    }

    public Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public void checkIfPictureIsAlreadyExist(String date) throws PictureNotFoundException {
        if (this.displayPictureDate(date) == null) {
            this.getDataFromNasaApi(date);
        } else {
            System.out.println("error");
        }
    }

    public void getDataFromNasaApi(String date) {
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
                data.get("copyright"),
                data.get("mediaType"),
                data.get("hdurl"),
                data.get("url")
        );
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

    @ApiIgnore
    @GetMapping("/admin/reset")
    public void resetPictureData() {
        this.galacticPicturesService.resetAllPictureData();
    }
}
