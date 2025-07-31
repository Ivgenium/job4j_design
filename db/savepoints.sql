create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

truncate table products restart identity;

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 10, 10);
insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 20, 20);
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 30, 30);

select * from products;

begin transaction;
insert into products (name, producer, count, price)
VALUES ('product_4', 'producer_4', 40, 40);
select * from products;
savepoint first_savepoint;
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 30, 30);
select * from products;
rollback to first_savepoint;
select * from products;
rollback;

begin transaction;
savepoint my_savepoint;
update products set price = price * 5;
savepoint my_savepoint;
update products set price = price * 2;
savepoint my_savepoint;
update products set price = price * 3;
select * from products;

rollback to my_savepoint;
select * from products;

release my_savepoint;
rollback to my_savepoint;
select * from products;

release my_savepoint;
rollback to my_savepoint;
select * from products;

rollback;
select * from products;