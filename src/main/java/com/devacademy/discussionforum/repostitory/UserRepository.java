package com.devacademy.discussionforum.repostitory;

import com.devacademy.discussionforum.dto.UserRequest;
import com.devacademy.discussionforum.dto.UserResponse;
import com.jooq.discussionforum.Tables;
import org.springframework.stereotype.Repository;
import org.jooq.DSLContext;

@Repository
public class UserRepository {

    private final DSLContext dsl;

    public UserRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public UserResponse save(UserRequest user) {
        return dsl.insertInto(Tables.USERS, Tables.USERS.USERNAME, Tables.USERS.PASSWORD_HASH)
                .values(user.username(), user.password())
                .returningResult(Tables.USERS.ID, Tables.USERS.USERNAME, Tables.USERS.IS_ADMIN)
                .fetchOneInto(UserResponse.class);
    }
}
