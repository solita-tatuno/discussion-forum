package com.devacademy.discussionforum.repostitory;

import com.devacademy.discussionforum.dto.AddMessage;
import com.devacademy.discussionforum.dto.MessageUpdate;
import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Messages;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {

    private final DSLContext dsl;

    public MessageRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Messages save(AddMessage message) {
        return dsl.insertInto(Tables.MESSAGES,
                        Tables.MESSAGES.MESSAGE,
                        Tables.MESSAGES.TOPIC_ID,
                        Tables.MESSAGES.USER_ID,
                        Tables.MESSAGES.UP_VOTES
                )
                .values(message.message(), message.topicId(), message.userId(), message.upVotes())
                .returning()
                .fetchOneInto(Messages.class);
    }

    public Messages update(Integer messageId, MessageUpdate message) {
        return dsl.update(Tables.MESSAGES)
                .set(Tables.MESSAGES.MESSAGE, message.message())
                .set(Tables.MESSAGES.UP_VOTES, message.upVotes())
                .where(Tables.MESSAGES.ID.eq(messageId))
                .returning()
                .fetchOneInto(Messages.class);
    }
}
