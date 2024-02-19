package com.devacademy.discussionforum.repository;

import com.devacademy.discussionforum.TestHelper;
import com.devacademy.discussionforum.repostitory.MessageRepository;
import com.jooq.discussionforum.tables.pojos.Messages;
import com.jooq.discussionforum.tables.pojos.Topics;
import com.jooq.discussionforum.tables.pojos.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
public class MessageRepositoryTests extends TestHelper {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void savesMessageWhenValidMessage() {
        Users user = super.createUser("newUser");
        Topics topic = super.createTopic("newTopic", user);

        Messages message = new Messages(null, user.getId(), topic.getId(), "newMessage", 0, null, null);
        Messages newMessage = messageRepository.save(message);

        String query = "SELECT * FROM messages WHERE message = ?";
        List<Messages> messages = dsl.fetch(query, message.getMessage()).into(Messages.class);

        assertEquals(1, messages.size(), "There should be only one message");
        assertEquals(newMessage.getMessage(), messages.get(0).getMessage(), "Message should match");
        assertEquals(newMessage.getUserId(), messages.get(0).getUserId(), "MessageUserId should match");
        assertEquals(newMessage.getTopicId(), messages.get(0).getTopicId(), "MessageTopicId should match");
    }
}
