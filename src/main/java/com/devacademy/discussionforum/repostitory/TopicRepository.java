package com.devacademy.discussionforum.repostitory;

import com.devacademy.discussionforum.dto.*;
import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Topics;
import org.jooq.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.*;

@Repository
public class TopicRepository {

    private final DSLContext dsl;

    public TopicRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Topics save(AddTopic topic) {
        return dsl.insertInto(Tables.TOPICS, Tables.TOPICS.USER_ID, Tables.TOPICS.NAME)
                .values(topic.userId(), topic.name())
                .returning()
                .fetchOneInto(Topics.class);
    }

    public List<TopicWithUser> findAll() {
        return dsl.select(Tables.TOPICS.ID,
                        Tables.TOPICS.NAME,
                        Tables.TOPICS.CREATED_AT,
                        Tables.TOPICS.UPDATED_AT,
                        row(Tables.USERS.ID, Tables.USERS.USERNAME, Tables.USERS.IS_ADMIN)
                                .mapping(UserResponse::new))
                .from(Tables.TOPICS)
                .join(Tables.USERS).on(Tables.TOPICS.USER_ID.eq(Tables.USERS.ID))
                .fetch(Records.mapping(TopicWithUser::new));
    }

    public Optional<SingleTopic> findOne(Integer id) {
        return dsl.select(Tables.TOPICS.ID,
                        Tables.TOPICS.NAME,
                        Tables.TOPICS.CREATED_AT,
                        Tables.TOPICS.UPDATED_AT,
                        row(
                                Tables.USERS.ID,
                                Tables.USERS.USERNAME,
                                Tables.USERS.IS_ADMIN)
                                .mapping(UserResponse::new),
                        array(
                                select(
                                        row(
                                                Tables.MESSAGES.ID,
                                                Tables.MESSAGES.TOPIC_ID,
                                                Tables.MESSAGES.MESSAGE,
                                                Tables.MESSAGES.UP_VOTES,
                                                Tables.MESSAGES.CREATED_AT,
                                                Tables.MESSAGES.UPDATED_AT,
                                                row(
                                                        Tables.USERS.ID,
                                                        Tables.USERS.USERNAME,
                                                        Tables.USERS.IS_ADMIN)
                                                        .mapping(UserResponse::new)

                                        )
                                                .mapping(MessageWithUser.class, MessageWithUser::new))
                                        .from(Tables.MESSAGES)
                                        .where(Tables.MESSAGES.TOPIC_ID.eq(Tables.TOPICS.ID))
                        )
                )
                .from(Tables.TOPICS)
                .join(Tables.USERS).on(Tables.TOPICS.USER_ID.eq(Tables.USERS.ID))
                .where(Tables.TOPICS.ID.eq(id))
                .fetchOptional(Records.mapping(SingleTopic::new));
    }

    public int deleteOne(Integer id) {
        dsl.deleteFrom(Tables.MESSAGES)
                .where(Tables.MESSAGES.TOPIC_ID.eq(id))
                .execute();

        return dsl.deleteFrom(Tables.TOPICS)
                .where(Tables.TOPICS.ID.eq(id))
                .execute();
    }

    public Optional<Topics> update(Integer id, AddTopic topic) {
        return Optional.ofNullable(dsl.update(Tables.TOPICS)
                .set(Tables.TOPICS.NAME, topic.name())
                .where(Tables.TOPICS.ID.eq(id))
                .returning()
                .fetchOneInto(Topics.class));
    }
}
