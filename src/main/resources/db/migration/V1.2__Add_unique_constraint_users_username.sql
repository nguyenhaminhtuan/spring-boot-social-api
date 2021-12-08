ALTER TABLE users
    ADD CONSTRAINT UQ_users_username
    UNIQUE (username);