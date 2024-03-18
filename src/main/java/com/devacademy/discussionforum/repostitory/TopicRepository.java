package com.devacademy.discussionforum.repostitory;

import com.devacademy.discussionforum.dto.*;
import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Topics;
import org.jooq.*;
import org.springframework.data.domain.Pageable;
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

    public Topics create(Integer userId, TopicDataDTO topic) {
        return dsl.insertInto(Tables.TOPICS)
                .set(Tables.TOPICS.USER_ID, userId)
                .set(Tables.TOPICS.NAME, topic.name())
                .returning()
                .fetchOneInto(Topics.class);
    }

    public TopicsDTO getAll(Pageable pageable) {
        int totalRows = countTotalTopics();

        List<UserTopicDTO> topics = dsl.select(Tables.TOPICS.ID,
                        Tables.TOPICS.NAME,
                        Tables.TOPICS.CREATED_AT,
                        Tables.TOPICS.UPDATED_AT,
                        row(Tables.USERS.ID, Tables.USERS.USERNAME, Tables.USERS.IS_ADMIN)
                                .mapping(UserDTO::new),
                        count(Tables.MESSAGES.ID).as("messageCount"),
                        max(Tables.MESSAGES.CREATED_AT).as("lastMessageTime"))
                .from(Tables.TOPICS)
                .join(Tables.USERS).on(Tables.TOPICS.USER_ID.eq(Tables.USERS.ID))
                .leftJoin(Tables.MESSAGES)
                .on(Tables.MESSAGES.TOPIC_ID.eq(Tables.TOPICS.ID))
                .groupBy(Tables.TOPICS.ID,
                        Tables.TOPICS.NAME,
                        Tables.TOPICS.CREATED_AT,
                        Tables.TOPICS.UPDATED_AT,
                        Tables.USERS.ID,
                        Tables.USERS.USERNAME,
                        Tables.USERS.IS_ADMIN)
                .orderBy(max(Tables.MESSAGES.CREATED_AT).desc().nullsLast())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch(Records.mapping(UserTopicDTO::new));

        return new TopicsDTO(totalRows, topics);
    }

    public int countTotalTopics() {
        return dsl.fetchCount(dsl.selectFrom(Tables.TOPICS));
    }

    public int deleteOne(Integer id) {
        return dsl.deleteFrom(Tables.TOPICS)
                .where(Tables.TOPICS.ID.eq(id))
                .execute();
    }

    public Optional<Topics> update(Integer id, TopicDataDTO topic) {
        return Optional.ofNullable(dsl.update(Tables.TOPICS)
                .set(Tables.TOPICS.NAME, topic.name())
                .where(Tables.TOPICS.ID.eq(id))
                .returning()
                .fetchOneInto(Topics.class));
    }
}
