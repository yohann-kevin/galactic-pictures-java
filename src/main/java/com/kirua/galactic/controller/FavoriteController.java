package com.kirua.galactic.controller;

import com.kirua.galactic.domain.user.User;
import com.kirua.galactic.service.FavoriteService;
import com.kirua.galactic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {
    private FavoriteService favoriteService;

    private UserService userService;

    @GetMapping()
    public ArrayList<Object> findFavoriteByUser(Principal principal) {
        User currentUser = this.userService.getUserByName(principal.getName());
        return this.favoriteService.findFavoriteByUser(currentUser);
    }
}
