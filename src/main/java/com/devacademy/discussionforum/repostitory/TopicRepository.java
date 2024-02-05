package com.devacademy.discussionforum.repostitory;

import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Topics;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TopicRepository {

    @Autowired
    DSLContext dsl;

    public Topics save(Topics topic) {
        return dsl.insertInto(Tables.TOPICS, Tables.TOPICS.USER_ID, Tables.TOPICS.NAME)
                .values(topic.getUserId(), topic.getName())
                .returning()
                .fetchOneInto(Topics.class);
    }
}
