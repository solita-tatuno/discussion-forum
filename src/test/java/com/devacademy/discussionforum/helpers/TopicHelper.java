package com.devacademy.discussionforum.helpers;

import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Topics;
import com.jooq.discussionforum.tables.pojos.Users;
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
        dsl.truncate(Tables.TOPICS).cascade().execute();
    }

    public Topics createTopic(String topicName, Users user) {
        String query = "INSERT INTO topics (user_id, name) VALUES (?, ?) RETURNING *";
        return Objects.requireNonNull(dsl.fetchOne(query, user.getId(), topicName)).into(Topics.class);
    }

    public List<Topics> getAllTopics() {
        String query = "SELECT * FROM topics";
        return dsl.fetch(query).into(Topics.class);
    }
}
