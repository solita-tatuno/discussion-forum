package com.devacademy.discussionforum.repository;

import com.devacademy.discussionforum.dto.AddMessage;
import com.devacademy.discussionforum.helpers.MessageHelper;
import com.devacademy.discussionforum.helpers.TopicHelper;
import com.devacademy.discussionforum.helpers.UserHelper;
import com.devacademy.discussionforum.repostitory.MessageRepository;
import com.jooq.discussionforum.tables.pojos.Messages;
import com.jooq.discussionforum.tables.pojos.Topics;
import com.jooq.discussionforum.tables.pojos.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
public class MessageRepositoryTests {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private TopicHelper topicHelper;

    @Autowired
    private MessageHelper messageHelper;

    @BeforeEach
    void setUp() {
        userHelper.clearTable();
        topicHelper.clearTable();
        messageHelper.clearTable();
    }

    @Test
    void savesMessageWhenValidMessage() {
        Users user = userHelper.createUser("newUser");
        Topics topic = topicHelper.createTopic("newTopic", user);
        AddMessage message = new AddMessage(topic.getId(), "newMessage", 0, user.getId());

        Messages insertedMessage = messageRepository.save(message);

        List<Messages> messages = messageHelper.findMessagesByMessage(message.message());

        assertEquals(1, messages.size(), "There should be only one message");
        assertEquals(insertedMessage.getMessage(), messages.get(0).getMessage(), "Message should match");
        assertEquals(insertedMessage.getUserId(), messages.get(0).getUserId(), "MessageUserId should match");
        assertEquals(insertedMessage.getTopicId(), messages.get(0).getTopicId(), "MessageTopicId should match");
    }
}
