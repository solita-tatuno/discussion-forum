CREATE TABLE forum_user
(
    id            SERIAL PRIMARY KEY,
    username      VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(60) NOT NULL,
    is_admin      BOOLEAN     NOT NULL DEFAULT FALSE
)
