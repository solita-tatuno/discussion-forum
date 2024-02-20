package com.devacademy.discussionforum.repostitory;

import com.devacademy.discussionforum.dto.MessageRequest;
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

    public Messages save(MessageRequest message) {
        return dsl.insertInto(Tables.MESSAGES,
                        Tables.MESSAGES.MESSAGE,
                        Tables.MESSAGES.TOPIC_ID,
                        Tables.MESSAGES.USER_ID,
                        Tables.MESSAGES.UP_VOTES)
                .values(message.message(), message.topicId(), message.userId(), message.upVotes())
                .returning()
                .fetchOneInto(Messages.class);
    }
}
