CREATE TABLE invites (
 id          UUID PRIMARY KEY,
 tenant_id   VARCHAR(100) NOT NULL,
 email       VARCHAR(255) NOT NULL,
 role        VARCHAR(50)  NOT NULL,
 token_hash  VARCHAR(255) NOT NULL UNIQUE,
 status      VARCHAR(50)  NOT NULL,
 expires_at  TIMESTAMP    NOT NULL,
 created_at  TIMESTAMP    NOT NULL
);