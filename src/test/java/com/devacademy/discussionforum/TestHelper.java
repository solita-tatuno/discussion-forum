package com.devacademy.discussionforum;

import com.jooq.discussionforum.Tables;
import com.jooq.discussionforum.tables.pojos.Messages;
import com.jooq.discussionforum.tables.pojos.Topics;
import com.jooq.discussionforum.tables.pojos.Users;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Objects;

@SpringBootTest
@Testcontainers
public abstract class TestHelper {

    @Autowired
    protected DSLContext dsl;

    @BeforeEach
    void clearTables() {
        dsl.truncate(Tables.USERS,
                Tables.TOPICS,
                Tables.MESSAGES
        ).cascade().execute();
    }

    public Users createUser(String username) {
        String query = "INSERT INTO users (username, password_hash, is_admin) VALUES (?, ?, ?) RETURNING *";
        return Objects.requireNonNull(dsl.fetchOne(query, username, "password", false)).into(Users.class);
    }

    public Topics createTopic(String topicName, Users user) {
        String query = "INSERT INTO topics (user_id, name) VALUES (?, ?) RETURNING *";
        return Objects.requireNonNull(dsl.fetchOne(query, user.getId(), topicName)).into(Topics.class);
    }

    public Messages createMessage(String message, Users user, Topics topic) {
        String query = "INSERT INTO messages (user_id, topic_id, message, up_votes) VALUES (?, ?, ?, ?) RETURNING *";
        return Objects.requireNonNull(dsl.fetchOne(query, user.getId(), topic.getId(), message, 0)).into(Messages.class);
    }
}
