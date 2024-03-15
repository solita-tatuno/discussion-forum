package com.devacademy.discussionforum.repository;

import com.devacademy.discussionforum.dto.AddMessage;
import com.devacademy.discussionforum.dto.MessageUpdate;
import com.devacademy.discussionforum.dto.MessagesDTO;
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
import org.springframework.data.domain.Pageable;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void savesMessage() {
        Users user = userHelper.createUser("newUser");
        Topics topic = topicHelper.createTopic("newTopic", user);
        AddMessage newMessage = new AddMessage(topic.getId(), "newMessage", 0, user.getId());

        messageRepository.save(newMessage);

        List<Messages> messages = messageHelper.getAllMessages();

        assertEquals(1, messages.size(), "There should be only one message");
        assertEquals(newMessage.message(), messages.get(0).getMessage(), "Message should match");
        assertEquals(newMessage.userId(), messages.get(0).getUserId(), "MessageUserId should match");
        assertEquals(newMessage.topicId(), messages.get(0).getTopicId(), "MessageTopicId should match");
    }

    @Test
    void updatesMessage() {
        Users user = userHelper.createUser("newUser");
        Topics topic = topicHelper.createTopic("newTopic", user);
        Messages newMessage = messageHelper.createMessage("newMessage", user, topic);
        MessageUpdate messageUpdate = new MessageUpdate("updatedMessage", 0, newMessage.getUserId());

        messageRepository.update(newMessage.getId(), messageUpdate);

        List<Messages> messages = messageHelper.getAllMessages();

        assertEquals(1, messages.size(), "There should be only one message");
        assertEquals(messageUpdate.message(), messages.get(0).getMessage(), "Message should match");
        assertEquals(messageUpdate.userId(), messages.get(0).getUserId(), "MessageUserId should match");
        assertEquals(newMessage.getTopicId(), messages.get(0).getTopicId(), "Message should belong to the same topic as before");
    }

    @Test
    void updateReturnsEmptyIfInvalidId() {
        MessageUpdate messageUpdate = new MessageUpdate("updatedMessage", 0, 1);
        Optional<Messages> updatedMessage = messageRepository.update(0, messageUpdate);

        assertTrue(updatedMessage.isEmpty(), "Updated message should be empty");
    }

    @Test
    void deletesTopicMessagesWhenValidTopicId() {
        Users user = userHelper.createUser("newUser");
        Topics topic = topicHelper.createTopic("newTopic", user);

        messageHelper.createMessage("newMessage", user, topic);
        messageHelper.createMessage("newMessage2", user, topic);

        int rowsDeleted = messageRepository.deleteTopicMessages(topic.getId());

        List<Messages> messages = messageHelper.getAllMessages();

        assertEquals(2, rowsDeleted, "2 messages should be deleted");
        assertEquals(0, messages.size(), "Messages should be deleted");
    }

    @Test
    void getTopicMessagesReturnsAllTopicMessagesWithCorrectTotal() {
        Users user = userHelper.createUser("newUser");
        Topics topic = topicHelper.createTopic("newTopic", user);

        messageHelper.createMessage("newMessage", user, topic);
        messageHelper.createMessage("newMessage2", user, topic);

        MessagesDTO messages = messageRepository.getTopicMessages(topic.getId(), Pageable.ofSize(10));

        assertEquals(2, messages.totalCount(), "There should be 2 messages");
        assertEquals(2, messages.messages().size(), "There should be 2 messages");
    }
}
