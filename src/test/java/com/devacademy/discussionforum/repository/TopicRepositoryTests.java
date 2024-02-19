package com.devacademy.discussionforum.repository;

import com.devacademy.discussionforum.TestHelper;
import com.devacademy.discussionforum.dto.SingleTopic;
import com.devacademy.discussionforum.dto.TopicWithUser;
import com.devacademy.discussionforum.repostitory.TopicRepository;
import com.jooq.discussionforum.tables.pojos.Messages;
import com.jooq.discussionforum.tables.pojos.Topics;
import com.jooq.discussionforum.tables.pojos.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class TopicRepositoryTests extends TestHelper {

    @Autowired
    private TopicRepository topicRepository;

    @Test
    void savesTopicWhenValidTopic() {
        Users user = super.createUser("newUser");

        Topics topic = new Topics(null, user.getId(), "newTopic", null, null);
        Topics newTopic = topicRepository.save(topic);

        String query = "SELECT * FROM topics WHERE name = ?";
        List<Topics> topics = dsl.fetch(query, topic.getName()).into(Topics.class);

        assertEquals(1, topics.size(), "There should be only one topic");
        assertEquals(newTopic.getName(), topics.get(0).getName(), "Topic name should match");
        assertEquals(newTopic.getUserId(), topics.get(0).getUserId(), "TopicUserId should match");
    }

    @Test
    void findsAllTopics() {
        Users user = super.createUser("newUser");

        Topics topic = super.createTopic("newTopic", user);
        Topics topic2 = super.createTopic("newTopic2", user);

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
        Users user = super.createUser("newUser");
        Topics topic = super.createTopic("newTopic", user);
        Messages message = super.createMessage("newMessage", user, topic);

        SingleTopic singleTopic = topicRepository.findOne(topic.getId());

        assertEquals(topic.getName(), singleTopic.name(), "Topic name should match");
        assertEquals(user.getId(), singleTopic.user().id(), "UserId should match");
        assertEquals(1, singleTopic.messages().length, "There should be 1 message");
        assertEquals(message.getMessage(), singleTopic.messages()[0].message(), "Message should match");
    }
}
