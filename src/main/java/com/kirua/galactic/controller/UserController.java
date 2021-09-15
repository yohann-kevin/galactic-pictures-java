package com.kirua.galactic.controller;

import com.kirua.galactic.domain.user.User;
import com.kirua.galactic.dto.SignUp;
import com.kirua.galactic.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:8080")
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

    @GetMapping("current-user")
    public HashMap findCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = this.getUserByName(username);
        HashMap userInfo = new HashMap();
        userInfo.put("id", currentUser.getId());
        userInfo.put("login", currentUser.getLogin());
        userInfo.put("role", currentUser.getRole());
        return userInfo;
    }

    public User getUserByName(String name) {
        return this.userService.getUserByName(name);
    }
}
