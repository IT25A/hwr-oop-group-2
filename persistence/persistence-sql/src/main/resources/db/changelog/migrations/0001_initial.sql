--liquibase formatted sql

--changeset system:1 dbms:postgresql
CREATE TABLE rummikub_games (
	id VARCHAR(255) PRIMARY KEY,
  game jsonb NOT NULL
);