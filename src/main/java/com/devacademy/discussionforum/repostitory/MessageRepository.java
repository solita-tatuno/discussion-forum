package com.devacademy.discussionforum.repostitory;

import com.devacademy.discussionforum.dto.AddMessageDTO;
import com.devacademy.discussionforum.dto.MessageUpdateDTO;
import com.devacademy.discussionforum.dto.UserMessageDTO;
import com.devacademy.discussionforum.dto.UserDTO;
import com.devacademy.discussionforum.dto.MessagesDTO;
import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Messages;
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

    public Messages create(Integer userId, AddMessageDTO message) {
        return dsl.insertInto(Tables.MESSAGES)
                .set(Tables.MESSAGES.MESSAGE, message.message())
                .set(Tables.MESSAGES.TOPIC_ID, message.topicId())
                .set(Tables.MESSAGES.USER_ID, userId)
                .set(Tables.MESSAGES.UP_VOTES, message.upVotes())
                .returning()
                .fetchOneInto(Messages.class);
    }

    public Optional<Messages> update(Integer messageId, MessageUpdateDTO message) {
        return dsl.update(Tables.MESSAGES)
                .set(Tables.MESSAGES.MESSAGE, message.message())
                .set(Tables.MESSAGES.UP_VOTES, message.upVotes())
                .where(Tables.MESSAGES.ID.eq(messageId))
                .returning()
                .fetchOptionalInto(Messages.class);
    }

    public int deleteTopicMessages(Integer topicId) {
        return dsl.deleteFrom(Tables.MESSAGES)
                .where(Tables.MESSAGES.TOPIC_ID.eq(topicId))
                .execute();
    }

    public MessagesDTO getTopicMessages(Integer id, Pageable pageable) {
        List<UserMessageDTO> messages = dsl.select(
                        Tables.MESSAGES.ID,
                        Tables.MESSAGES.TOPIC_ID,
                        Tables.MESSAGES.MESSAGE,
                        Tables.MESSAGES.UP_VOTES,
                        Tables.MESSAGES.CREATED_AT,
                        Tables.MESSAGES.UPDATED_AT,
                        row(
                                Tables.USERS.ID,
                                Tables.USERS.USERNAME,
                                Tables.USERS.IS_ADMIN
                        )
                                .mapping(UserDTO::new))
                .from(Tables.MESSAGES)
                .join(Tables.USERS).on(Tables.MESSAGES.USER_ID.eq(Tables.USERS.ID))
                .where(Tables.MESSAGES.TOPIC_ID.eq(id))
                .orderBy(Tables.MESSAGES.CREATED_AT.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch(Records.mapping(UserMessageDTO::new));

        int totalCount = dsl.fetchCount(selectFrom(Tables.MESSAGES).where(Tables.MESSAGES.TOPIC_ID.eq(id)));

        return new MessagesDTO(messages, totalCount);
    }
}
