create table counterparties(
    id serial primary key,
    name varchar(255)
);

create table nomenclature(
	id serial primary key,
	name varchar(255)
);

create table orders(
	id serial primary key,
	counterparty_id int references counterparties(id),
	nomenclature_id int references nomenclature(id),
	quantity int,
	price decimal(15,2)
);