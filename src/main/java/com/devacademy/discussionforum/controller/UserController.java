package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.security.CustomUserDetails;
import com.devacademy.discussionforum.dto.UserRequest;
import com.devacademy.discussionforum.dto.UserResponse;
import com.devacademy.discussionforum.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse addUser(@RequestBody @Valid UserRequest user) {
        return userService.addUser(user);
    }

    @GetMapping("/me")
    public UserResponse getCurrentUser(Authentication authentication) {
        CustomUserDetails user = userService.loadUserByUsername(authentication.getName());
        return new UserResponse(user.getId(), user.getUsername(), user.isAdmin());
    }
}
