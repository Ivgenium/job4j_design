create table departments(
	id serial primary key,
	name varchar(255)
);

create table employees(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)	
);

insert into departments(name) values
	('Администрация'),
	('Бухгалтерия'),
	('Отдел кадров'),
	('IT'),
	('Продажи'),
	('Охрана труда'),
	('Снабжение');

insert into employees(name, department_id) values
	('Сидоров', 1),
	('Петров', 1),
	('Фомина', 2),
	('Иванова', 2),
	('Захарова', 3),
	('Морозова', 3),
	('Гришечкин', 4),
	('Перепёлкин', 4),
	('Кургина', 5),
	('Хусаинов', 5),
	('Неизвестно', null);

select * from departments as d
left join employees as e on d.id = e.department_id;

select * from departments as d
right join employees as e on d.id = e.department_id;

select * from departments as d
full join employees as e on d.id = e.department_id;

select * from departments as d
cross join employees as e;


select * from departments as d
left join employees as e on d.id = e.department_id
where e is null;


select d.name as "Департамент", e.name as "Сотрудник" 
from departments as d
left join employees as e on d.id = e.department_id;

select d.name as "Департамент", e.name as "Сотрудник" 
from employees as e
right join departments as d on e.department_id = d.id;