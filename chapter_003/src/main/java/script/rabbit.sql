CREATE DATABASE alert_rabbit;

-- === TABLES INITIATION BLOCK ===

CREATE TABLE rabbit (
	id serial primary key,
	created_date int(2000) not null
);