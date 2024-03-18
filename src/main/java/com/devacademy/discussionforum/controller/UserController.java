package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.security.CustomUserDetails;
import com.devacademy.discussionforum.dto.AddUserDTO;
import com.devacademy.discussionforum.dto.UserDTO;
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
    public UserDTO addUser(@RequestBody @Valid AddUserDTO user) {
        return userService.addUser(user);
    }

    @GetMapping("/me")
    public UserDTO getCurrentUser(Authentication authentication) {
        CustomUserDetails user = userService.loadUserByUsername(authentication.getName());
        return new UserDTO(user.getId(), user.getUsername(), user.isAdmin());
    }
}
