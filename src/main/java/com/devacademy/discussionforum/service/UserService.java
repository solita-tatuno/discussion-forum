package com.devacademy.discussionforum.service;

import com.devacademy.discussionforum.dto.UserResponse;
import com.devacademy.discussionforum.repostitory.UserRepository;
import com.jooq.discussionforum.tables.pojos.Users;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse addUser(Users user) {
        return userRepository.save(user);
    }
}
