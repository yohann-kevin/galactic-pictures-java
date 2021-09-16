package com.kirua.galactic.controller;

import com.kirua.galactic.domain.user.User;
import com.kirua.galactic.dto.SignUp;
import com.kirua.galactic.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public HashMap findCurrentUser(HttpSession session) {
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = this.getUserByName(username);
        HashMap userInfo = new HashMap();
        userInfo.put("id", currentUser.getId());
        userInfo.put("login", currentUser.getLogin());
        userInfo.put("role", currentUser.getRole());
        userInfo.put("session_id", session.getId());
        return userInfo;
    }

    @GetMapping("/session")
    public HashMap initSession(HttpSession session) {
//        String sessionId = session.getId();
        System.out.println("controller :");
        System.out.println(session.getId());
        HashMap sessionInfo = new HashMap();
        sessionInfo.put("session_id", session.getId());
        return sessionInfo;
    }

    public User getUserByName(String name) {
        return this.userService.getUserByName(name);
    }
}
