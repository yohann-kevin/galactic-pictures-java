package com.kirua.galactic.controller;

import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.service.GalacticPicturesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
