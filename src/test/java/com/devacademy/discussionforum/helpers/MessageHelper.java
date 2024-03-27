package com.devacademy.discussionforum.helpers;

import com.devacademy.discussionforum.jooq.Tables;
import com.devacademy.discussionforum.jooq.tables.pojos.Message;
import com.devacademy.discussionforum.jooq.tables.pojos.Topic;
import com.devacademy.discussionforum.jooq.tables.pojos.ForumUser;
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
        dsl.truncate(Tables.MESSAGE).cascade().execute();
    }

    public Message createMessage(String message, ForumUser user, Topic topic) {
        String query = "INSERT INTO message (user_id, topic_id, message, up_votes) VALUES (?, ?, ?, ?) RETURNING *";
        return Objects.requireNonNull(dsl.fetchOne(query, user.getId(), topic.getId(), message, 0)).into(Message.class);
    }

    public List<Message> getAllMessages() {
        String query = "SELECT * FROM message";
        return dsl.fetch(query).into(Message.class);
    }
}
