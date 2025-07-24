create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace function tax_after()
	returns trigger as
$$
	BEGIN
		update products
		set price = price * (1 + 0.2)
		where id = (select id from inserted);
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_after_insert
	after insert
	on products
	referencing  new table as inserted
	for each statement
	execute procedure tax_after();

insert into products(name, producer, count, price) values
	('product_1', 'producer_1', 2, 100);

select *
from products;

create
or replace function tax_before()
	returns trigger as
$$
	BEGIN		
		new.price = new.price * (1 + 0.2);
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_before_insert
	before insert
	on products
	for each row
	execute procedure tax_before();

insert into products(name, producer, count, price) values
	('product_2', 'producer_2', 2, 200);

select *
from products;

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

create
or replace function write_history_of_price()
	returns trigger as
$$
	BEGIN
		insert into history_of_price(name, price, date) values
		(new.name, new.price, now());
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger write_history_of_price
	after insert
	on products
	for each row
	execute procedure write_history_of_price();

insert into products(name, producer, count, price) values
	('product_3', 'producer_3', 3, 300);

select *
from history_of_price;