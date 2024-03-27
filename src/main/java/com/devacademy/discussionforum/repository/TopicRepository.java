package com.devacademy.discussionforum.repository;

import com.devacademy.discussionforum.dto.*;
import com.devacademy.discussionforum.jooq.Tables;
import com.devacademy.discussionforum.jooq.tables.pojos.Topic;
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

    public Topic create(Integer userId, TopicDataDTO topic) {
        return dsl.insertInto(Tables.TOPIC)
                .set(Tables.TOPIC.USER_ID, userId)
                .set(Tables.TOPIC.NAME, topic.name())
                .returning()
                .fetchOneInto(Topic.class);
    }

    public TopicsDTO getAll(Pageable pageable) {
        int totalRows = countTotalTopics();

        List<UserTopicDTO> topics = dsl.select(Tables.TOPIC.ID,
                        Tables.TOPIC.NAME,
                        Tables.TOPIC.CREATED_AT,
                        Tables.TOPIC.UPDATED_AT,
                        row(Tables.FORUM_USER.ID, Tables.FORUM_USER.USERNAME, Tables.FORUM_USER.IS_ADMIN)
                                .mapping(UserDTO::new),
                        count(Tables.MESSAGE.ID).as("messageCount"),
                        max(Tables.MESSAGE.CREATED_AT).as("lastMessageTime"))
                .from(Tables.TOPIC)
                .join(Tables.FORUM_USER).on(Tables.TOPIC.USER_ID.eq(Tables.FORUM_USER.ID))
                .leftJoin(Tables.MESSAGE)
                .on(Tables.MESSAGE.TOPIC_ID.eq(Tables.TOPIC.ID))
                .groupBy(Tables.TOPIC.ID,
                        Tables.TOPIC.NAME,
                        Tables.TOPIC.CREATED_AT,
                        Tables.TOPIC.UPDATED_AT,
                        Tables.FORUM_USER.ID,
                        Tables.FORUM_USER.USERNAME,
                        Tables.FORUM_USER.IS_ADMIN)
                .orderBy(max(Tables.MESSAGE.CREATED_AT).desc().nullsLast())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch(Records.mapping(UserTopicDTO::new));

        return new TopicsDTO(totalRows, topics);
    }

    public int countTotalTopics() {
        return dsl.fetchCount(dsl.selectFrom(Tables.TOPIC));
    }

    public int deleteOne(Integer id) {
        return dsl.deleteFrom(Tables.TOPIC)
                .where(Tables.TOPIC.ID.eq(id))
                .execute();
    }

    public Optional<Topic> update(Integer id, TopicDataDTO topic) {
        return Optional.ofNullable(dsl.update(Tables.TOPIC)
                .set(Tables.TOPIC.NAME, topic.name())
                .where(Tables.TOPIC.ID.eq(id))
                .returning()
                .fetchOneInto(Topic.class));
    }

    public Optional<TopicDTO> findOne(Integer id) {
        return dsl.select(Tables.TOPIC.ID,
                        Tables.TOPIC.NAME,
                        Tables.TOPIC.CREATED_AT,
                        Tables.TOPIC.UPDATED_AT,
                        row(
                                Tables.FORUM_USER.ID,
                                Tables.FORUM_USER.USERNAME,
                                Tables.FORUM_USER.IS_ADMIN
                        )
                                .mapping(UserDTO::new))
                .from(Tables.TOPIC)
                .join(Tables.FORUM_USER).on(Tables.TOPIC.USER_ID.eq(Tables.FORUM_USER.ID))
                .where(Tables.TOPIC.ID.eq(id))
                .fetchOptionalInto(TopicDTO.class);
    }
}
