package com.devacademy.discussionforum.repository;

import com.devacademy.discussionforum.dto.AddMessageDTO;
import com.devacademy.discussionforum.dto.MessageUpdateDTO;
import com.devacademy.discussionforum.dto.UserMessageDTO;
import com.devacademy.discussionforum.dto.UserDTO;
import com.devacademy.discussionforum.dto.MessagesDTO;
import com.devacademy.discussionforum.jooq.Tables;
import com.devacademy.discussionforum.jooq.tables.pojos.Message;
import org.jooq.DSLContext;
import org.jooq.Records;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.*;

@Repository
public class MessageRepository {

    private final DSLContext dsl;

    public MessageRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Message create(Integer userId, AddMessageDTO message) {
        return dsl.insertInto(Tables.MESSAGE)
                .set(Tables.MESSAGE.MESSAGE_, message.message())
                .set(Tables.MESSAGE.TOPIC_ID, message.topicId())
                .set(Tables.MESSAGE.USER_ID, userId)
                .set(Tables.MESSAGE.UP_VOTES, message.upVotes())
                .returning()
                .fetchOneInto(Message.class);
    }

    public Optional<Message> update(Integer messageId, MessageUpdateDTO message) {
        return dsl.update(Tables.MESSAGE)
                .set(Tables.MESSAGE.MESSAGE_, message.message())
                .set(Tables.MESSAGE.UP_VOTES, message.upVotes())
                .where(Tables.MESSAGE.ID.eq(messageId))
                .returning()
                .fetchOptionalInto(Message.class);
    }

    public int deleteTopicMessages(Integer topicId) {
        return dsl.deleteFrom(Tables.MESSAGE)
                .where(Tables.MESSAGE.TOPIC_ID.eq(topicId))
                .execute();
    }

    public MessagesDTO getTopicMessages(Integer id, Pageable pageable) {
        List<UserMessageDTO> messages = dsl.select(
                        Tables.MESSAGE.ID,
                        Tables.MESSAGE.TOPIC_ID,
                        Tables.MESSAGE.MESSAGE_,
                        Tables.MESSAGE.UP_VOTES,
                        Tables.MESSAGE.CREATED_AT,
                        Tables.MESSAGE.UPDATED_AT,
                        row(
                                Tables.FORUM_USER.ID,
                                Tables.FORUM_USER.USERNAME,
                                Tables.FORUM_USER.IS_ADMIN
                        )
                                .mapping(UserDTO::new))
                .from(Tables.MESSAGE)
                .join(Tables.FORUM_USER).on(Tables.MESSAGE.USER_ID.eq(Tables.FORUM_USER.ID))
                .where(Tables.MESSAGE.TOPIC_ID.eq(id))
                .orderBy(Tables.MESSAGE.CREATED_AT.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch(Records.mapping(UserMessageDTO::new));

        int totalCount = dsl.fetchCount(selectFrom(Tables.MESSAGE).where(Tables.MESSAGE.TOPIC_ID.eq(id)));

        return new MessagesDTO(messages, totalCount);
    }
}
