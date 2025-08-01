CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

insert into customers(first_name, last_name, age, country) values
	('Иван', 'Петров', 35, 'РФ'),
	('Владимир', 'Сидоров', 37, 'РФ'),
	('Сергей', 'Хусаинов', 35, 'Казахстан'),
	('Александр', 'Смирнов', 38, 'Беларусь'),
	('Надежда', 'Васильева', 36, 'Украина');

select * from customers where age = (select min(age) from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders(amount, customer_id) values
	(10, 1),
	(20, 2),
	(10, 1),
	(20, 2),
	(30, 3);

select * from customers where id not in (select distinct customer_id from orders);
