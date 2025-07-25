create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    END
$$;

create
or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products
            set count = count - u_count
            where id = u_id;
        end if;
        if
            tax > 0 THEN
            update products
            set price = price + price * tax;
        end if;
    END;
$$;

call insert_data('product_1', 'producer_1', 10, 100);
call insert_data('product_2', 'producer_2', 20, 200);

select * from products;

call update_data(5, 0, 1);
call update_data(0, 0.2, 2);

select * from products;

create
or replace procedure delete_empty_lines_at_products()
language 'plpgsql'
as $$
	BEGIN
		delete from products
		where name is null
			and producer is null
			and count = 0
			and price = 0;
	END;
$$;

call insert_data(null, null, 0, 0);

select * from products;

call delete_empty_lines_at_products();

select * from products;

create
or replace function delete_data_by_id(u_id integer)
returns void
language 'plpgsql'
as $$
	BEGIN
		delete from products
		where id = u_id;
	END;
$$;

call insert_data('product_3', 'producer_3', 10, 300);

select * from products;

select delete_data_by_id(3);

select * from products;