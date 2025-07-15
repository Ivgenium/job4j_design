create table teens (
	id serial primary key,
	name varchar(255),
	gender bool
);

insert into teens(name, gender) values
	('Маша', false),
	('Вася', true),
	('Марина', false),
	('Серёжа', true),
	('Лена', false),
	('Женя', true);

select t1.name, t2.name 
from teens as t1 cross join teens as t2
where t1.gender != t2.gender and t1.id < t2.id;