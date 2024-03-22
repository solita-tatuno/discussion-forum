CREATE TABLE topic
(
    id   SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES forum_user(id),
    name VARCHAR(75) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
)
