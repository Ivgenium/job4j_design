create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values
	('Horse', 10000, null),
	('Buffalo', 10000, null),
	('California Condor', 21600, '1797-01-01'),	
	('Duckbill', 3600, '1821-01-01'),
	('White-winged penguin', 10000, '1874-01-01'),
	('Pearl gourami', 3000, '1898-01-01'),
	('Amur whitefish', 3600, '1906-01-01'),
	('Eastern tuna', 4300, '1920-01-01'),
	('Leopard catfish', 2800, '1933-01-01'),
	('Malaysian Dolphin', 11000, '1956-01-01'),
	('Chinese ram', 5000, '1963-01-01');

select * from fauna
where name like '%fish%';

select * from fauna
where avg_age >= 10000 and avg_age <= 21000;

select * from fauna
where discovery_date is null;

select * from fauna
where discovery_date < '1950-01-01';