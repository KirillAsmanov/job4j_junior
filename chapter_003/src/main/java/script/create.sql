CREATE DATABASE items;

-- === USER-ROLE-RULES INITIATION BLOCK: ===

CREATE TABLE role (
	id serial primary key,
	name varchar(2000)
);

CREATE TABLE user (
	id serial primary key,
	login varchar(2000),
	password varchar(2000),
	role_id int references role(id)
);

CREATE TABLE rules (
	id serial primary key,
	name varchar(2000)
);

CREATE TABLE rules_to_role (
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);


-- === ITEMS DATA INITIATION BLOCK: ===

CREATE TABLE state (
	id serial primary key,
	text varchar(2000),
);

CREATE TABLE category (
	id serial primary key,
	text varchar(2000),
);

CREATE TABLE item (
	id serial primary key,
	text varchar(2000),
	time timestamp,
	user_id int references user(id),
	category_id int references category(id),
	state_id int references state(id)
);

CREATE TABLE comments (
	id serial primary key,
	text varchar(2000),
	time timestamp,
	item_id int references item(id)
);

CREATE TABLE attachment (
	id serial primary key,
	text varchar(2000),
	time timestamp,
	item_id int references item(id)
);


-- === INSERT VALUES BLOCK ===

INSERT INTO rules (name) values ('create item');
INSERT INTO rules (name) values ('change state');

INSERT INTO rules_to_role (role_id, rules_id) values ('1', '1');
INSERT INTO rules_to_role (role_id, rules_id) values ('1', '2');
INSERT INTO rules_to_role (role_id, rules_id) values ('2', '1');

INSERT INTO users (login, password, role_id) values ('Kirill Asmanov', 'password', '1');
INSERT INTO users (login, password, role_id) values ('Ivan Ivanov', 'qwerty', '2');

INSERT INTO category (text) values ('programing category');
INSERT INTO state (text) values ('open');

INSERT INTO item (text, time, user_id, category_id, state_id) values ('FIRST ITEM', '1997-01-08 04:05:06', '1', '1', '1');

INSERT INTO attachment (text, time, item_id) values ('FIRST ATTACHMENT', '1997-01-08 04:05:06', '1');
INSERT INTO comments (text, time, item_id) values ('FIRST COMMENT', '1997-01-08 04:05:06', '1');