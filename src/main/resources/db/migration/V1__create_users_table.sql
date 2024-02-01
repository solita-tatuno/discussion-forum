CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    username      VARCHAR(50) NOT NULL,
    password_hash VARCHAR(50) NOT NULL,
    is_admin      BOOLEAN     NOT NULL DEFAULT FALSE
)
