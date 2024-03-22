package com.devacademy.discussionforum.repository;


import com.devacademy.discussionforum.dto.AddUserDTO;
import com.devacademy.discussionforum.dto.UserDTO;
import com.devacademy.discussionforum.helpers.UserHelper;
import com.devacademy.discussionforum.jooq.tables.pojos.ForumUser;
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
        AddUserDTO user = new AddUserDTO("newUser", "password");

        UserDTO newUser = userRepository.create(user, "hashedPassword");

        List<ForumUser> users = userHelper.getAllUsers();
        assertEquals(1, users.size(), "There should be only one user");

        assertEquals(newUser.username(), users.get(0).getUsername(), "Username should match");
        assertEquals(newUser.id(), users.get(0).getId(), "UserId should match");
    }
}
