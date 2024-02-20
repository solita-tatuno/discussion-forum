package com.devacademy.discussionforum.repository;

import com.devacademy.discussionforum.dto.SingleTopic;
import com.devacademy.discussionforum.dto.TopicRequest;
import com.devacademy.discussionforum.dto.TopicWithUser;
import com.devacademy.discussionforum.helpers.MessageHelper;
import com.devacademy.discussionforum.helpers.TopicHelper;
import com.devacademy.discussionforum.helpers.UserHelper;
import com.devacademy.discussionforum.repostitory.TopicRepository;
import com.jooq.discussionforum.tables.pojos.Messages;
import com.jooq.discussionforum.tables.pojos.Topics;
import com.jooq.discussionforum.tables.pojos.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class TopicRepositoryTests {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private MessageHelper messageHelper;

    @Autowired
    private TopicHelper topicHelper;

    @BeforeEach
    void setUp() {
        userHelper.clearTable();
        topicHelper.clearTable();
        messageHelper.clearTable();
    }

    @Test
    void savesTopicWhenValidTopic() {
        Users user = userHelper.createUser("newUser");

        TopicRequest topic = new TopicRequest(user.getId(), "newTopic");
        Topics newTopic = topicRepository.save(topic);

        List<Topics> topics = topicHelper.findTopicsByName(topic.name());

        assertEquals(1, topics.size(), "There should be only one topic");
        assertEquals(newTopic.getName(), topics.get(0).getName(), "Topic name should match");
        assertEquals(newTopic.getUserId(), topics.get(0).getUserId(), "TopicUserId should match");
    }

    @Test
    void findsAllTopics() {
        Users user = userHelper.createUser("newUser");

        Topics topic = topicHelper.createTopic("newTopic", user);
        Topics topic2 = topicHelper.createTopic("newTopic2", user);

        List<TopicWithUser> topics = topicRepository.findAll();
        assertEquals(2, topics.size(), "There should be 2 topics");

        boolean topicFound = topics.stream()
                .anyMatch(t -> t.name().equals(topic.getName()) && Objects.equals(t.user().id(), user.getId()));
        boolean topic2Found = topics.stream()
                .anyMatch(t -> t.name().equals(topic2.getName()) && Objects.equals(t.user().id(), user.getId()));

        assertTrue(topicFound, "Topic should be found");
        assertTrue(topic2Found, "Topic 2 should be found");
    }

    @Test
    void findsSingleTopicWhenValidId() {
        Users user = userHelper.createUser("newUser");
        Topics topic = topicHelper.createTopic("newTopic", user);
        Messages message = messageHelper.createMessage("newMessage", user, topic);

        SingleTopic singleTopic = topicRepository.findOne(topic.getId());

        assertEquals(topic.getName(), singleTopic.name(), "Topic name should match");
        assertEquals(user.getId(), singleTopic.user().id(), "UserId should match");
        assertEquals(1, singleTopic.messages().length, "There should be 1 message");
        assertEquals(message.getMessage(), singleTopic.messages()[0].message(), "Message should match");
    }
}
