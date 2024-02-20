package com.devacademy.discussionforum.helpers;

import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Messages;
import com.jooq.discussionforum.tables.pojos.Topics;
import com.jooq.discussionforum.tables.pojos.Users;
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

    public List<Messages> findMessagesByMessage(String message) {
        String query = "SELECT * FROM messages WHERE message = ?";
        return dsl.fetch(query, message).into(Messages.class);
    }
}