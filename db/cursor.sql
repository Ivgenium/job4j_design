create table products
(
    id    serial primary key,
    name  varchar(50),
    count integer default 0,
    price integer
);

truncate table products restart identity;

insert into products (name, count, price)
VALUES ('product_1', 1, 5);
insert into products (name, count, price)
VALUES ('product_2', 2, 10);
insert into products (name, count, price)
VALUES ('product_3', 3, 15);
insert into products (name, count, price)
VALUES ('product_4', 4, 20);
insert into products (name, count, price)
VALUES ('product_5', 5, 25);
insert into products (name, count, price)
VALUES ('product_6', 6, 30);
insert into products (name, count, price)
VALUES ('product_7', 7, 35);
insert into products (name, count, price)
VALUES ('product_8', 8, 40);
insert into products (name, count, price)
VALUES ('product_9', 9, 45);
insert into products (name, count, price)
VALUES ('product_10', 10, 50);
insert into products (name, count, price)
VALUES ('product_11', 11, 55);
insert into products (name, count, price)
VALUES ('product_12', 12, 60);
insert into products (name, count, price)
VALUES ('product_13', 13, 65);
insert into products (name, count, price)
VALUES ('product_14', 14, 70);
insert into products (name, count, price)
VALUES ('product_15', 15, 75);
insert into products (name, count, price)
VALUES ('product_16', 16, 80);
insert into products (name, count, price)
VALUES ('product_17', 17, 85);
insert into products (name, count, price)
VALUES ('product_18', 18, 90);
insert into products (name, count, price)
VALUES ('product_19', 19, 95);
insert into products (name, count, price)
VALUES ('product_20', 20, 100);

select * from products;

begin transaction; 
declare
curs_products scroll cursor for
select * from products;

move ABSOLUTE 20 from curs_products;
fetch 0 from curs_products;

move backward 5 from curs_products;
fetch 0 from curs_products;

move backward 8 from curs_products;
fetch 0 from curs_products;

move backward 5 from curs_products;
fetch 0 from curs_products;

move backward 1 from curs_products;
fetch 0 from curs_products;

rollback;