create table nomenclature(
	id serial primary key,
	name varchar(255),
	used bool,
	price decimal(15, 2)
);

insert into nomenclature(name, used, price) values ('Jeep', false, 25000.00);

update nomenclature set used = true;

delete from nomenclature;

select * from nomenclature;