package com.devacademy.discussionforum.repository;


import com.devacademy.discussionforum.dto.UserResponse;
import com.devacademy.discussionforum.repostitory.UserRepository;
import com.jooq.discussionforum.tables.pojos.Users;
import org.jooq.DSLContext;
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
    private DSLContext dsl;

    @Autowired
    private UserRepository userRepository;

    @Test
    void savesUserWhenValidUser() {
        String username = "newUser";
        Users user = new Users(null, username, "password", false);

        UserResponse newUser = userRepository.save(user);

        String query = "SELECT * FROM users WHERE username = ?";
        List<Users> users = dsl.fetch(query, username).into(Users.class);

        assertEquals(1, users.size(), "There should be only one user");

        assertEquals(username, users.get(0).getUsername(), "Username should match");
        assertEquals(newUser.id(), users.get(0).getId(), "UserId should match");
    }
}
