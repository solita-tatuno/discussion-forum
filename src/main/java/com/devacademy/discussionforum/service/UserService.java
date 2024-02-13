package com.devacademy.discussionforum.service;

import com.devacademy.discussionforum.dto.UsersResponse;
import com.devacademy.discussionforum.repostitory.UserRepository;
import com.jooq.discussionforum.tables.pojos.Users;
import com.jooq.discussionforum.tables.records.UsersRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UsersResponse addUser(Users user) {
        UsersRecord record = userRepository.save(user);
        return new UsersResponse(record.getId(), record.getUsername(), record.getIsAdmin());
    }
}
