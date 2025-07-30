begin transaction isolation level serializable;

select * from products;

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 50, 50);

select * from products;

commit;

select * from products;
