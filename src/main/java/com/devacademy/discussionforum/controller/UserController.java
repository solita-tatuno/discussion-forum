package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.dto.UsersResponse;
import com.devacademy.discussionforum.service.UserService;
import com.jooq.discussionforum.tables.pojos.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UsersResponse> addUser(@RequestBody Users user) {
        UsersResponse newUser = userService.addUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
