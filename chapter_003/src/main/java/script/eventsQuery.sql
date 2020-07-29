-- 1. Есть таблица встреч(id, name), есть таблица юзеров(id, name).
-- Нужно доработать модель базы данных, так чтобы пользователи могли учавствовать во встречах.
-- У каждого участника встречи может быть разный статус участия - например подтвердил участие, отклонил.

-- 2. Нужно написать запрос, который получит список всех заяков и количество подтвердивших участников.
-- 3. Нужно получить все совещания, где не было ни одной заявки на посещения

-- table init block

CREATE TABLE users (
	id serial primary key,
	name varchar(2000) not null
);

create table events (
	id serial primary key,
	name varchar(2000) not null
);

CREATE TABLE requests (
	id serial primary key,
	users_id int references users(id),
    events_id int references events(id),
	status boolean not null
);


-- data init block

insert into users (name) values ('Kirill');
insert into users (name) values ('Karina');
insert into users (name) values ('Polina');

insert into events (name) values ('Hackaton');
insert into events (name) values ('Birthday');

insert into requests (users_id, events_id, status) values ('1', '1', 'true');
insert into requests (users_id, events_id, status) values ('1', '2', 'false');
insert into requests (users_id, events_id, status) values ('2', '1', 'false');
insert into requests (users_id, events_id, status) values ('2', '2', 'false');
insert into requests (users_id, events_id, status) values ('3', '1', 'true');
insert into requests (users_id, events_id, status) values ('3', '2', 'false');


-- first query:

select ev.name, COALESCE(t.qty_users, 0) AS users_cnt
from events AS ev
left join (select e.name as events, COUNT(*) as qty_users
			from requests as r
			left join events as e on (r.events_id = e.id)
			where status = 'true'
			group by e.id) as t
	on ev.name = t.events


-- second query:

select ev.name, COALESCE(t.qty_users, 0) AS users_cnt
from events AS ev
left join (select e.name as events, COUNT(*) as qty_users
			from requests as r
			left join events as e on (r.events_id = e.id)
			where status = 'true'
			group by e.id) as t
	on ev.name = t.events
where COALESCE(t.qty_users, 0) = '0';