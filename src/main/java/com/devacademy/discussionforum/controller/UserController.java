package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.dto.UserResponse;
import com.devacademy.discussionforum.service.UserService;
import com.jooq.discussionforum.tables.pojos.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody Users user) {
        UserResponse newUser = userService.addUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
