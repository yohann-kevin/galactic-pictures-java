package com.kirua.galactic.controller;

import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.exception.PictureNotFoundException;
import com.kirua.galactic.service.GalacticPicturesService;
import com.kirua.galactic.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("/open/picture")
public class OpenPictureController {
    private GalacticPicturesService galacticPicturesService;

    private TokenService tokenService;

    @GetMapping
    public Object displayAllPicture(@RequestParam(value = "token") String token) {
        Boolean tokenExist = this.tokenService.verifyTokenExist(token);
        if (tokenExist) {
            ArrayList<GalacticPictures> galacticPicturesList = this.galacticPicturesService.findAll();
            return galacticPicturesList;
        } else {
            return this.tokenService.invalidKey(token);
        }
    }

    @GetMapping("/date")
    public Object displayPictureDate(@RequestParam String date, String token) throws PictureNotFoundException {
        Boolean tokenExist = this.tokenService.verifyTokenExist(token);
        if (tokenExist) {
            GalacticPictures galacticPicture = this.galacticPicturesService.findByDate(date);
            return galacticPicture;
        } else {
            return this.tokenService.invalidKey(token);
        }
    }

    @GetMapping("/between/date")
    public Object findPictureBetweenTwoDate(@RequestParam String start, String end) {
        ArrayList<GalacticPictures> pictures = this.galacticPicturesService.findBetweenTwoDate(start, end);
        return pictures;
    }

    @GetMapping("/id")
    public Object displayPictureById(@RequestParam String uuid, String token) throws PictureNotFoundException {
        Boolean tokenExist = this.tokenService.verifyTokenExist(token);
        if (tokenExist) {
            GalacticPictures galacticPictures = this.galacticPicturesService.findById(uuid);
            return galacticPictures;
        } else {
            return this.tokenService.invalidKey(token);
        }
    }
}
