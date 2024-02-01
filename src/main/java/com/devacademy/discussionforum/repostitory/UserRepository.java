package com.devacademy.discussionforum.repostitory;

import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Users;
import com.jooq.discussionforum.tables.records.UsersRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.jooq.DSLContext;

@Repository
public class UserRepository {

    @Autowired
    DSLContext dsl;

    public UsersRecord save(Users user) {
        return dsl.insertInto(Tables.USERS, Tables.USERS.USERNAME, Tables.USERS.PASSWORD_HASH, Tables.USERS.IS_ADMIN)
                .values(user.getUsername(), user.getPasswordHash(), user.getIsAdmin())
                .returning()
                .fetchOne();
    }
}
