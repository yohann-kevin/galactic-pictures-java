package com.kirua.galactic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    @PostMapping("/sign-up")
    public String singUp() {
        return "plop";
    }

    @GetMapping("/user-memory")
    public String getUserMemory() {
        return "plop";
    }
}
