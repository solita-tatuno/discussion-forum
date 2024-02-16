package com.devacademy.discussionforum;

import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Users;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.testcontainers.junit.jupiter.Testcontainers;

@Component
@SpringBootTest
@Testcontainers
public abstract class TestHelper {

    @Autowired
    protected DSLContext dsl;

    @BeforeEach
    void clearTables() {
        dsl.truncate(Tables.USERS, Tables.TOPICS).cascade().execute();
    }

    public Users createUser(String username) {
        Users user = new Users(null, username, "password", false);
        return dsl.insertInto(Tables.USERS)
                .set(Tables.USERS.USERNAME, user.getUsername())
                .set(Tables.USERS.PASSWORD_HASH, user.getPasswordHash())
                .set(Tables.USERS.IS_ADMIN, user.getIsAdmin())
                .returning()
                .fetchOneInto(Users.class);
    }
}
