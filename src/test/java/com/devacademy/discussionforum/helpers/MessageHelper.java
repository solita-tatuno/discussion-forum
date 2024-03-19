package com.devacademy.discussionforum.helpers;

import com.devacademy.discussionforum.jooq.Tables;
import com.devacademy.discussionforum.jooq.tables.pojos.Messages;
import com.devacademy.discussionforum.jooq.tables.pojos.Topics;
import com.devacademy.discussionforum.jooq.tables.pojos.Users;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Objects;

@Component
@SpringBootTest
@Testcontainers
public class MessageHelper {

    @Autowired
    private DSLContext dsl;

    public void clearTable() {
        dsl.truncate(Tables.MESSAGES).cascade().execute();
    }

    public Messages createMessage(String message, Users user, Topics topic) {
        String query = "INSERT INTO messages (user_id, topic_id, message, up_votes) VALUES (?, ?, ?, ?) RETURNING *";
        return Objects.requireNonNull(dsl.fetchOne(query, user.getId(), topic.getId(), message, 0)).into(Messages.class);
    }

    public List<Messages> getAllMessages() {
        String query = "SELECT * FROM messages";
        return dsl.fetch(query).into(Messages.class);
    }
}
