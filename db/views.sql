create table nomenclature(
	id serial primary key,
	name varchar(255)
);

create table receipt_of_goods(
	id serial primary key,
	date timestamp,
	nomenclature_id int references nomenclature(id),
	quantity float
);

create table sale_of_goods(
	id serial primary key,
	date timestamp,
	nomenclature_id int references nomenclature(id),
	quantity float
);

insert into nomenclature(name) values 
	('Эскимо пломбир ванильный в молочном шоколаде'),
	('Молоко 2,5% в бутылке, 900 мл'),
	('Картофель вес'),
	('Морковь вес'),
	('Апельсины вес'),
	('Арбузы вес');

insert into receipt_of_goods(date, nomenclature_id, quantity) values
	('2025-07-14 09:15:00', 1, 25),
	('2025-07-14 09:15:00', 2, 10),
	('2025-07-14 09:15:00', 3, 12.50),
	('2025-07-14 09:15:00', 4, 6.50),
	('2025-07-14 09:15:00', 5, 10.50),
	('2025-07-15 10:05:00', 1, 15),
	('2025-07-15 10:05:00', 2, 15),
	('2025-07-15 10:05:00', 3, 10.50),
	('2025-07-15 10:05:00', 4, 4.50),
	('2025-07-15 10:05:00', 5, 5.50);

insert into sale_of_goods(date, nomenclature_id, quantity) values
	('2025-07-14 11:34:52', 1, 2),
	('2025-07-14 12:10:04', 1, 1),
	('2025-07-14 13:49:25', 1, 3),
	('2025-07-14 18:44:29', 2, 1),
	('2025-07-14 18:52:02', 3, 2.50),
	('2025-07-14 18:56:05', 4, 0.50),
	('2025-07-14 18:59:09', 5, 1.50),
	('2025-07-14 19:01:12', 3, 1.25),
	('2025-07-14 19:02:13', 4, 0.40),
	('2025-07-14 19:05:14', 5, 1.50),
	('2025-07-15 11:24:52', 1, 1),
	('2025-07-15 12:15:06', 1, 2),
	('2025-07-15 13:44:15', 1, 2),
	('2025-07-15 18:24:29', 2, 2),
	('2025-07-15 18:42:02', 3, 3.20),
	('2025-07-15 18:46:05', 4, 0.40),
	('2025-07-15 18:49:09', 5, 1.20),
	('2025-07-15 19:00:16', 3, 1.15),
	('2025-07-15 19:06:43', 4, 0.60),
	('2025-07-15 19:15:34', 5, 1.30);


create view current_stock_balances
as
select n.id,
	   n.name,
	   coalesce(((select sum(quantity) 
	   from receipt_of_goods as r
	   where n.id = r.nomenclature_id and r.date <= now())
	   - 
	   (select sum(quantity) 
	   from sale_of_goods as s
	   where n.id = s.nomenclature_id and s.date <= now())), 0) as quantity 
from nomenclature as n;

select *
from current_stock_balances as s
where id = 1;
