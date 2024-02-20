package com.devacademy.discussionforum.repository;

import com.devacademy.discussionforum.dto.MessageRequest;
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

        MessageRequest message = new MessageRequest(user.getId(), topic.getId(), "newMessage", 0);
        Messages newMessage = messageRepository.save(message);

        List<Messages> messages = messageHelper.findMessagesByMessage(message.message());

        assertEquals(1, messages.size(), "There should be only one message");
        assertEquals(newMessage.getMessage(), messages.get(0).getMessage(), "Message should match");
        assertEquals(newMessage.getUserId(), messages.get(0).getUserId(), "MessageUserId should match");
        assertEquals(newMessage.getTopicId(), messages.get(0).getTopicId(), "MessageTopicId should match");
    }
}
