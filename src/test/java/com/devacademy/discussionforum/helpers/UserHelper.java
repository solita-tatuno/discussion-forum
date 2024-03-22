package com.devacademy.discussionforum.helpers;

import com.devacademy.discussionforum.jooq.Tables;
import com.devacademy.discussionforum.jooq.tables.pojos.ForumUser;
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
        dsl.truncate(Tables.FORUM_USER).cascade().execute();
    }

    public ForumUser createUser(String username) {
        String query = "INSERT INTO forum_user (username, password_hash, is_admin) VALUES (?, ?, ?) RETURNING *";
        return Objects.requireNonNull(dsl.fetchOne(query, username, "password", false)).into(ForumUser.class);
    }

    public List<ForumUser> getAllUsers() {
        String query = "SELECT * FROM forum_user";
        return dsl.fetch(query).into(ForumUser.class);
    }
}
