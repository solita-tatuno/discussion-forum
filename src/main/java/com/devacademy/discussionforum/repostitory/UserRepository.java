package com.devacademy.discussionforum.repostitory;

import com.devacademy.discussionforum.dto.UserRequest;
import com.devacademy.discussionforum.dto.UserResponse;
import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Users;
import org.springframework.stereotype.Repository;
import org.jooq.DSLContext;

import java.util.Optional;

@Repository
public class UserRepository {

    private final DSLContext dsl;

    public UserRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public UserResponse save(UserRequest user, String hashedPassword) {
        return dsl.insertInto(Tables.USERS, Tables.USERS.USERNAME, Tables.USERS.PASSWORD_HASH)
                .values(user.username(), hashedPassword)
                .returningResult(Tables.USERS.ID, Tables.USERS.USERNAME, Tables.USERS.IS_ADMIN)
                .fetchOneInto(UserResponse.class);
    }

    public Optional<Users> findByUsername(String username) {
        return dsl.select(Tables.USERS.fields())
                .from(Tables.USERS)
                .where(Tables.USERS.USERNAME.eq(username))
                .fetchOptionalInto(Users.class);
    }
}
