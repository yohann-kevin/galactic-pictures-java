package com.kirua.galactic.controller;

import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.exception.PictureNotFoundException;
import com.kirua.galactic.service.GalacticPicturesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("/open/picture")
public class OpenPicture {
    private GalacticPicturesService galacticPicturesService;

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
}
