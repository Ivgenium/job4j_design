create table car_bodies(
	id serial primary key,
	name varchar(255)
);

create table car_engines(
	id serial primary key,
	name varchar(255)
);

create table car_transmissions(
	id serial primary key,
	name varchar(255)
);

create table cars(
	id serial primary key,
	name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values 
	('Седан'),
	('Универсал'),
	('Хетчбэк'),
	('Лимузин'),
	('Кабриолет');

insert into car_engines(name) values 
	('Бензиновый'),
	('Дизельный'),
	('Гибридный'),
	('Электрический'),
	('Водородный');

insert into car_transmissions(name) values
	('Механическая'),
	('Автоматическая'),
	('Бесступенчатая'),
	('Роботизированная');

insert into cars(name, body_id, engine_id, transmission_id) values
	('BMW 5', 1, 2, 2),
	('BMW 3', 2, 2, 2),
	('Toyota Prius', 3, 3, 2),
	('Toyota Camry', 1, 1, 1),
	('Lada Ellada', 2, 4, null),
	('Lada Iskra', 1, 1, 1);

select c.id, c.name as car_name, b.name as body_name, e.name as engine_name, t.name as transmission_name
from cars as c
	left join car_bodies as b on c.body_id = b.id
	left join car_engines as e on c.engine_id = e.id
	left join car_transmissions as t on c.transmission_id = t.id;

select b.id, b.name
from car_bodies as b left join cars as c on b.id = c.body_id
where c is null;

select e.id, e.name
from car_engines as e left join cars as c on e.id = c.engine_id
where c is null;

select t.id, t.name
from car_transmissions as t left join cars as c on t.id = c.transmission_id
where c is null;