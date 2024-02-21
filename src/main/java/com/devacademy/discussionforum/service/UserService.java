package com.devacademy.discussionforum.service;

import com.devacademy.discussionforum.dto.UserRequest;
import com.devacademy.discussionforum.dto.UserResponse;
import com.devacademy.discussionforum.exception.ResourceNotFoundException;
import com.devacademy.discussionforum.repostitory.UserRepository;
import com.jooq.discussionforum.tables.pojos.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse addUser(UserRequest user) {
        String hashedPassword = passwordEncoder.encode(user.password());
        return userRepository.save(user, hashedPassword);
    }

    public String loginUser(UserRequest user) {
        Optional<Users> userFromDb = userRepository.findByUsername(user.username());

        if (userFromDb.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        boolean passwordsMatch = passwordEncoder.matches(user.password(), userFromDb.get().getPasswordHash());

        return passwordsMatch ? "Login successful" : "Login failed";
    }
}
