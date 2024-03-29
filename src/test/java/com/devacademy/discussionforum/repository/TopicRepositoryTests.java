package com.devacademy.discussionforum.repository;

import com.devacademy.discussionforum.dto.*;
import com.devacademy.discussionforum.helpers.MessageHelper;
import com.devacademy.discussionforum.helpers.TopicHelper;
import com.devacademy.discussionforum.helpers.UserHelper;
import com.devacademy.discussionforum.jooq.tables.pojos.Topic;
import com.devacademy.discussionforum.jooq.tables.pojos.ForumUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

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
        ForumUser user = userHelper.createUser("newUser");

        TopicDataDTO topic = new TopicDataDTO("newTopic");
        topicRepository.create(user.getId(), topic);

        List<Topic> topics = topicHelper.getAllTopics();

        assertEquals(1, topics.size(), "There should be only one topic");
        assertEquals(topic.name(), topics.get(0).getName(), "Topic name should match");
        assertEquals(user.getId(), topics.get(0).getUserId(), "TopicUserId should match");
    }

    @Test
    void findsAllTopics() {
        ForumUser user = userHelper.createUser("newUser");

        Topic topic = topicHelper.createTopic("newTopic", user);
        Topic topic2 = topicHelper.createTopic("newTopic2", user);
        Pageable pageable = Pageable.ofSize(10);

        TopicsDTO dto = topicRepository.getAll(pageable);
        assertEquals(2, dto.topics().size(), "There should be 2 topics");

        boolean topicFound = dto.topics().stream()
                .anyMatch(t -> t.name().equals(topic.getName()) && Objects.equals(t.user().id(), user.getId()));
        boolean topic2Found = dto.topics().stream()
                .anyMatch(t -> t.name().equals(topic2.getName()) && Objects.equals(t.user().id(), user.getId()));

        assertTrue(topicFound, "Topic should be found");
        assertTrue(topic2Found, "Topic 2 should be found");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void correctMessageCountWhenTopicHasMessages(int numberOfMessages) {
        ForumUser user = userHelper.createUser("newUser");
        Topic topic = topicHelper.createTopic("newTopic", user);

        IntStream.range(0, numberOfMessages).forEach(i -> messageHelper.createMessage("Message " + i, user, topic));

        Pageable pageable = Pageable.ofSize(10);

        TopicsDTO dto = topicRepository.getAll(pageable);
        assertEquals(1, dto.topics().size(), "There should be 1 topic");

        UserTopicDTO singleTopic = dto.topics().get(0);
        assertEquals(numberOfMessages, singleTopic.messageCount(), "Message count should be " + numberOfMessages);
    }

    @Test
    void deletesTopicWhenValidId() {
        ForumUser user = userHelper.createUser("newUser");
        Topic topic = topicHelper.createTopic("newTopic", user);

        int deletedTopicRowsCount = topicRepository.deleteOne(topic.getId());
        List<Topic> topics = topicHelper.getAllTopics();

        assertEquals(1, deletedTopicRowsCount, "One row should be deleted");
        assertTrue(topics.isEmpty(), "There should be no topics");
    }

    @Test
    void exceptionIfDeletingTopicWithMessages() {
        ForumUser user = userHelper.createUser("newUser");
        Topic topic = topicHelper.createTopic("newTopic", user);
        messageHelper.createMessage("newMessage", user, topic);

        assertThrows(DataAccessException.class, () -> topicRepository.deleteOne(topic.getId()), "Should throw exception");
    }

    @Test
    void updatesTopicWhenValidId() {
        ForumUser user = userHelper.createUser("newUser");
        Topic topic = topicHelper.createTopic("newTopic", user);

        TopicDataDTO topicUpdate = new TopicDataDTO("updatedTopic");

        topicRepository.update(topic.getId(), topicUpdate);

        List<Topic> topicsInDb = topicHelper.getAllTopics();


        assertEquals(1, topicsInDb.size(), "There should be only one topic");
        assertEquals(topicUpdate.name(), topicsInDb.get(0).getName(), "Topic should have updated name");
        assertEquals(user.getId(), topicsInDb.get(0).getUserId(), "UserId should remain the same");
    }

    @Test
    void updateReturnsEmptyIfInvalidId() {
        TopicDataDTO topicUpdate = new TopicDataDTO("updatedTopic");
        Optional<Topic> updatedTopic = topicRepository.update(0, topicUpdate);

        assertTrue(updatedTopic.isEmpty(), "Updated topic should be empty");
    }
}
