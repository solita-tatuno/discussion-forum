package com.devacademy.discussionforum.helpers;

import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Users;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserHelper {

    @Autowired
    private DSLContext dsl;

    public void clearTable() {
        dsl.truncate(Tables.USERS).cascade().execute();
    }

    public Users createUser(String username) {
        String query = "INSERT INTO users (username, password_hash, is_admin) VALUES (?, ?, ?) RETURNING *";
        return Objects.requireNonNull(dsl.fetchOne(query, username, "password", false)).into(Users.class);
    }

    public List<Users> getAllUsers() {
        String query = "SELECT * FROM users";
        return dsl.fetch(query).into(Users.class);
    }
}
