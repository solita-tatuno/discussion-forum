package com.devacademy.discussionforum.repostitory;

import com.devacademy.discussionforum.dto.UserResponse;
import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Users;
import org.springframework.stereotype.Repository;
import org.jooq.DSLContext;

@Repository
public class UserRepository {

    private final DSLContext dsl;

    public UserRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public UserResponse save(Users user) {
        return dsl.insertInto(Tables.USERS, Tables.USERS.USERNAME, Tables.USERS.PASSWORD_HASH, Tables.USERS.IS_ADMIN)
                .values(user.getUsername(), user.getPasswordHash(), user.getIsAdmin())
                .returningResult(Tables.USERS.ID, Tables.USERS.USERNAME, Tables.USERS.IS_ADMIN)
                .fetchOneInto(UserResponse.class);
    }
}
