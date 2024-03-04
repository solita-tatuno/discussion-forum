package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.security.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final TokenService tokenService;

    public LoginController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public String login(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }
}
