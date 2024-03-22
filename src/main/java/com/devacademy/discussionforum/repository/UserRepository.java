package com.devacademy.discussionforum.repository;

import com.devacademy.discussionforum.dto.AddUserDTO;
import com.devacademy.discussionforum.dto.UserDTO;
import com.devacademy.discussionforum.jooq.Tables;
import com.devacademy.discussionforum.jooq.tables.pojos.ForumUser;
import org.springframework.stereotype.Repository;
import org.jooq.DSLContext;

import java.util.Optional;

@Repository
public class UserRepository {

    private final DSLContext dsl;

    public UserRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public UserDTO create(AddUserDTO user, String hashedPassword) {
        return dsl.insertInto(Tables.FORUM_USER)
                .set(Tables.FORUM_USER.USERNAME, user.username())
                .set(Tables.FORUM_USER.PASSWORD_HASH, hashedPassword)
                .returningResult(Tables.FORUM_USER.ID, Tables.FORUM_USER.USERNAME, Tables.FORUM_USER.IS_ADMIN)
                .fetchOneInto(UserDTO.class);
    }

    public Optional<ForumUser> findByUsername(String username) {
        return dsl.select(Tables.FORUM_USER.fields())
                .from(Tables.FORUM_USER)
                .where(Tables.FORUM_USER.USERNAME.eq(username))
                .fetchOptionalInto(ForumUser.class);
    }
}
