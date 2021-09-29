package com.kirua.galactic.controller;

import com.kirua.galactic.domain.pictures.GalacticPictures;
import com.kirua.galactic.exception.PictureNotFoundException;
import com.kirua.galactic.security.JwtUtil;
import com.kirua.galactic.service.GalacticPicturesService;
import com.kirua.galactic.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
        ArrayList<GalacticPictures> galacticPicturesList = this.galacticPicturesService.findAll();
        Boolean tokenExist = this.tokenService.verifyTokenExist(token);
        if (tokenExist) {
            return galacticPicturesList;
        } else {
            return this.invalidKey(token);
        }
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
