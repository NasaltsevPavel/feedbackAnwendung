CREATE TABLE IF NOT EXISTS feedback_entity
(
    id       VARCHAR(36) PRIMARY KEY,
    sender   VARCHAR(255) NOT NULL,
    receiver VARCHAR(255) NOT NULL,
    text     TEXT,
    meeting  BOOLEAN      NOT NULL,
    anonym   BOOLEAN      NOT NULL,
    active   BOOLEAN      NOT NULL
);
