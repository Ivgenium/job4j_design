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

begin transaction isolation level serializable;

select * from products;

insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 40, 40);

select * from products;

commit;

select * from products;
