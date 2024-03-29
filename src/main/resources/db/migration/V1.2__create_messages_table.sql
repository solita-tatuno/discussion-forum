CREATE TABLE message
(
    id   SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES forum_user(id) NOT NULL,
    topic_id INTEGER REFERENCES topic(id) NOT NULL,
    message VARCHAR(250) NOT NULL,
    up_votes INTEGER DEFAULT 0 NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
)
