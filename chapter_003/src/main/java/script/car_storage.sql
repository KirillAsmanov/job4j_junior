CREATE DATABASE car_storage;

-- === TABLES INITIATION BLOCK ===

CREATE TABLE car_body (
	id serial primary key,
	name varchar(2000) not null
);

CREATE TABLE car_transmission (
	id serial primary key,
	name varchar(2000) not null
);

CREATE TABLE car_engine (
	id serial primary key,
	name varchar(2000) not null,
	volume integer not null
);

CREATE TABLE car (
	id serial primary key,
	name varchar(2000) not null,
	body_id int references car_body(id) not null,
	transmission_id int references car_transmission(id) not null,
	engine_id int references car_engine(id) not null
);


-- === VALUES INSERT BLOCK ===

INSERT INTO car_body(name) values ('Седан');
INSERT INTO car_body(name) values ('Хэчбэк');
INSERT INTO car_body(name) values ('Универсал');

INSERT INTO car_transmission(name) values ('Механическая');
INSERT INTO car_transmission(name) values ('Ручная');

INSERT INTO car_engine(name, volume) values ('Renault K7M', '2');
INSERT INTO car_engine(name, volume) values ('Toyota 2AR-FE', '3');
INSERT INTO car_engine(name, volume) values ('Honda R20A', '3');

INSERT INTO car(name, body_id, transmission_id, engine_id) values ('Renault', '1', '1', '1');
INSERT INTO car(name, body_id, transmission_id, engine_id) values ('Toyota', '3', '1', '2');



-- === QUERY BLOCK ===

-- 1. Вывести список всех машин и все привязанные к ним детали:

SELECT c.name AS car, b.name AS body, t.name AS transmission, e.name AS engine
FROM car AS c 
	JOIN car_body AS b ON (c.body_id = b.id)
	JOIN car_transmission AS t ON (c.transmission_id = t.id)
	JOIN car_engine AS e ON (c.engine_id = e.id)

-- 2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач:

SELECT b.name AS body, t.name AS transmission, e.name AS engine
FROM car AS c
	FULL JOIN car_body AS b ON (c.body_id = b.id)
	FULL JOIN car_transmission AS t ON (c.transmission_id = t.id)
	FULL  JOIN car_engine AS e ON (c.engine_id = e.id)
WHERE c.id IS NULL