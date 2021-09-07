package com.kirua.galactic.controller;

import com.kirua.galactic.domain.user.User;
import com.kirua.galactic.dto.SignUp;
import com.kirua.galactic.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ArrayList findAll() {
        return this.userService.findAll();
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody SignUp signUp) {
        this.userService.signUp(signUp);
    }

    public User getUserByName(String name) {
        return this.userService.getUserByName(name);
    }
}
