package com.devacademy.discussionforum.repostitory;

import com.devacademy.discussionforum.dto.AddUserDTO;
import com.devacademy.discussionforum.dto.UserDTO;
import com.devacademy.discussionforum.jooq.Tables;
import com.devacademy.discussionforum.jooq.tables.pojos.Users;
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
        return dsl.insertInto(Tables.USERS)
                .set(Tables.USERS.USERNAME, user.username())
                .set(Tables.USERS.PASSWORD_HASH, hashedPassword)
                .returningResult(Tables.USERS.ID, Tables.USERS.USERNAME, Tables.USERS.IS_ADMIN)
                .fetchOneInto(UserDTO.class);
    }

    public Optional<Users> findByUsername(String username) {
        return dsl.select(Tables.USERS.fields())
                .from(Tables.USERS)
                .where(Tables.USERS.USERNAME.eq(username))
                .fetchOptionalInto(Users.class);
    }
}
