-- create table devices
-- (
--     id    serial primary key,
--     name  varchar(255),
--     price float
-- );

-- create table people
-- (
--     id   serial primary key,
--     name varchar(255)
-- );

-- create table devices_people
-- (
--     id        serial primary key,
--     device_id int references devices (id),
--     people_id int references people (id)
-- );

-- insert into people(name) values ('Вовик'), ('Васёк'), ('Ванёк');

-- insert into devices(name, price) values
-- 	('Мобильный телефон1', 1500.00),
-- 	('Мобильный телефон2', 2000.00),
-- 	('Мобильный телефон3', 2500.00),
-- 	('Ноутбук1', 5000.00),
-- 	('Ноутбук2', 6000.00),
-- 	('Ноутбук3', 7000.00),
-- 	('Телевизор1', 4500.00),
-- 	('Телевизор2', 5500.00),
-- 	('Телевизор3', 6500.00);
	
-- insert into devices_people(device_id, people_id) values
-- 	(1, 1),
-- 	(2, 2),
-- 	(3, 3),
-- 	(4, 1),
-- 	(5, 2),
-- 	(6, 3),
-- 	(7, 1),
-- 	(8, 2),
-- 	(9, 3);

select avg(d.price) as "Средняя цена устройств"
from devices as d;

select p.name as "Имя", avg(d.price) as "Средняя цена устройств"
from people as p join devices_people as dp on p.id = dp.people_id
	join devices as d on dp.device_id = d.id
group by p.name;

select p.name as "Имя", avg(d.price) as "Средняя цена устройств"
from people as p join devices_people as dp on p.id = dp.people_id
	join devices as d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000.00;