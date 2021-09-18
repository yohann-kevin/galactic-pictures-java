package com.kirua.galactic.controller;

import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.domain.user.User;
import com.kirua.galactic.exception.InvalidUuidException;
import com.kirua.galactic.exception.PictureNotFoundException;
import com.kirua.galactic.service.FavoriteService;
import com.kirua.galactic.service.GalacticPicturesService;
import com.kirua.galactic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {
    private FavoriteService favoriteService;

    private UserService userService;

    private GalacticPicturesService galacticPicturesService;

    @GetMapping
    public ArrayList<Object> findFavoriteByUser(Principal principal) {
        User currentUser = this.userService.getUserByName(principal.getName());
        return this.favoriteService.findFavoriteByUser(currentUser);
    }

    @DeleteMapping
    public void unlikePictureById(@RequestParam String pictureId, String id) throws InvalidUuidException {
        this.galacticPicturesService.unlikePicture(pictureId);
        this.favoriteService.unlikePictureById(id);
    }
}
