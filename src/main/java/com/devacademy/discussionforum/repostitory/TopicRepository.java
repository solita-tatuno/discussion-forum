package com.devacademy.discussionforum.repostitory;

import com.devacademy.discussionforum.model.TopicWithUser;
import com.devacademy.discussionforum.model.UsersResponse;
import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Topics;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.jooq.impl.DSL.row;

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

    public List<TopicWithUser> findAll() {
        return dsl.select(Tables.TOPICS.ID,
                        Tables.TOPICS.NAME,
                        Tables.TOPICS.CREATED_AT,
                        Tables.TOPICS.UPDATED_AT,
                        row(Tables.USERS.ID, Tables.USERS.USERNAME, Tables.USERS.IS_ADMIN)
                                .mapping(UsersResponse::new))
                .from(Tables.TOPICS)
                .join(Tables.USERS).on(Tables.TOPICS.USER_ID.eq(Tables.USERS.ID))
                .fetch(Records.mapping(TopicWithUser::new));
    }
}
