package com.devacademy.discussionforum.helpers;

import com.devacademy.discussionforum.jooq.Tables;
import com.devacademy.discussionforum.jooq.tables.pojos.Topic;
import com.devacademy.discussionforum.jooq.tables.pojos.ForumUser;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class TopicHelper {

    @Autowired
    private DSLContext dsl;

    public void clearTable() {
        dsl.truncate(Tables.TOPIC).cascade().execute();
    }

    public Topic createTopic(String topicName, ForumUser user) {
        String query = "INSERT INTO topic (user_id, name) VALUES (?, ?) RETURNING *";
        return Objects.requireNonNull(dsl.fetchOne(query, user.getId(), topicName)).into(Topic.class);
    }

    public List<Topic> getAllTopics() {
        String query = "SELECT * FROM topic";
        return dsl.fetch(query).into(Topic.class);
    }
}
