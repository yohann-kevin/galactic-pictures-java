package com.kirua.galactic.controller;

import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.exception.PictureNotFoundException;
import com.kirua.galactic.security.JwtUtil;
import com.kirua.galactic.service.GalacticPicturesService;
import com.kirua.galactic.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/open/picture")

public class OpenPictureController {
    private GalacticPicturesService galacticPicturesService;

    private TokenService tokenService;

    private final JwtUtil jwtUtil;

    @GetMapping
    public Object displayAllPicture(@RequestParam(value = "token") String token) {
        Boolean tokenExist = this.tokenService.verifyTokenExist(token);
        if (tokenExist) {
            ArrayList<GalacticPictures> galacticPicturesList = this.galacticPicturesService.findAll();
            return galacticPicturesList;
        } else {
            return this.invalidKey(token);
        }
    }

    @GetMapping("/date")
    public Object displayPictureDate(@RequestParam String date, String token) throws PictureNotFoundException {
        Boolean tokenExist = this.tokenService.verifyTokenExist(token);
        if (tokenExist) {
            GalacticPictures galacticPicture = this.galacticPicturesService.findByDate(date);
            return galacticPicture;
        } else {
            return this.invalidKey(token);
        }

    }

    @GetMapping("/id")
    public Object displayPictureById(@RequestParam String uuid, String token) throws PictureNotFoundException {
        Boolean tokenExist = this.tokenService.verifyTokenExist(token);
        if (tokenExist) {
            GalacticPictures galacticPictures = this.galacticPicturesService.findById(uuid);
            return galacticPictures;
        } else {
            return this.invalidKey(token);
        }
    }


    // Key management
    @GetMapping("/key")
    public HashMap generateOpenApiKey(Principal principal) {
        String name = principal.getName();
        String token = this.jwtUtil.generateJwtTokenForOpenApi(name);
        this.tokenService.registerToken(name, token);
        HashMap<String, String> tokenResponse = new HashMap<>();
        tokenResponse.put("name", name);
        tokenResponse.put("token", token);
        return tokenResponse;
    }

    @GetMapping("/key/find")
    public HashMap findTokenByUserName(Principal principal) {
        String name = principal.getName();
        return this.tokenService.findTokenByName(name);
    }

    @GetMapping("/key/regenerate")
    public HashMap regenerateToken(Principal principal) {
        String name = principal.getName();
        return this.tokenService.regenerateToken(name);
    }

    @GetMapping("/key/error")
    public HashMap invalidKey(String token) {
        HashMap<String, String> response = new HashMap<>();
        response.put("error", "Invalid token ! ");
        response.put("token", token);
        return response;
    }
}
