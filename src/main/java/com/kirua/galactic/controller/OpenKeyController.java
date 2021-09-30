package com.kirua.galactic.controller;

import com.kirua.galactic.security.JwtUtil;
import com.kirua.galactic.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/key")
public class OpenKeyController {
    private TokenService tokenService;

    private final JwtUtil jwtUtil;

    @GetMapping
    public HashMap generateOpenApiKey(Principal principal) {
        String name = principal.getName();
        String token = this.jwtUtil.generateJwtTokenForOpenApi(name);
        this.tokenService.registerToken(name, token);
        HashMap<String, String> tokenResponse = new HashMap<>();
        tokenResponse.put("name", name);
        tokenResponse.put("token", token);
        return tokenResponse;
    }

    @GetMapping("/find")
    public HashMap findTokenByUserName(Principal principal) {
        String name = principal.getName();
        return this.tokenService.findTokenByName(name);
    }

    @GetMapping("/regenerate")
    public HashMap regenerateToken(Principal principal) {
        String name = principal.getName();
        return this.tokenService.regenerateToken(name);
    }
}
