package com.devacademy.discussionforum.repository;

import com.devacademy.discussionforum.TestHelper;
import com.devacademy.discussionforum.repostitory.TopicRepository;
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
public class TopicRepositoryTests extends TestHelper {

    @Autowired
    private TopicRepository topicRepository;

    @Test
    void savesTopicWhenValidTopic() {
        String username = "newUser";
        String topicName = "newTopic";

        Users user = super.createUser(username);

        Topics topic = new Topics(null, user.getId(), topicName, null, null);
        Topics newTopic = topicRepository.save(topic);

        String query = "SELECT * FROM topics WHERE name = ?";
        List<Topics> topics = dsl.fetch(query, topicName).into(Topics.class);

        assertEquals(1, topics.size(), "There should be only one topic");

        assertEquals(topicName, topics.get(0).getName(), "Topic name should match");
        assertEquals(newTopic.getUserId(), topics.get(0).getUserId(), "TopicUserId should match");
    }
}
