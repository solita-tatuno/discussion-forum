package com.devacademy.discussionforum.repository;


import com.devacademy.discussionforum.dto.UserRequest;
import com.devacademy.discussionforum.dto.UserResponse;
import com.devacademy.discussionforum.helpers.UserHelper;
import com.devacademy.discussionforum.repostitory.UserRepository;
import com.jooq.discussionforum.tables.pojos.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHelper userHelper;

    @BeforeEach
    void setUp() {
        userHelper.clearTable();
    }

    @Test
    void savesUserWhenValidUser() {
        UserRequest user = new UserRequest("newUser", "password");

        UserResponse newUser = userRepository.save(user, "hashedPassword");

        List<Users> users = userHelper.getAllUsers();
        assertEquals(1, users.size(), "There should be only one user");

        assertEquals(newUser.username(), users.get(0).getUsername(), "Username should match");
        assertEquals(newUser.id(), users.get(0).getId(), "UserId should match");
    }
}
