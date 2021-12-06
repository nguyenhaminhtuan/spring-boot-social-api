CREATE TABLE users (
    id BIGINT NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(100) NOT NULL,
    avatar_url TEXT,
    cover_photo_url TEXT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    gender SMALLINT,
    enabled BOOLEAN NOT NULL DEFAULT FALSE,
    locked BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id)
);