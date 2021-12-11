CREATE TABLE followers (
    user_id BIGINT NOT NULL,
    follower_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, follower_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
)