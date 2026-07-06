--liquibase formatted sql
--changeset rummikub-team:2

CREATE TABLE rummikub_games (
                                id VARCHAR(255) PRIMARY KEY,
                                game jsonb NOT NULL
);