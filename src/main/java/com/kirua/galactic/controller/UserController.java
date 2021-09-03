package com.kirua.galactic.controller;

import com.kirua.galactic.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void add(@RequestParam String pseudo, String email, String password, boolean isAdmin, boolean isModerator) {
        this.userService.add(pseudo, email, password, isAdmin, isModerator);
    }

    @GetMapping
    public ArrayList findAll() {
        return this.userService.findAll();
    }
}
