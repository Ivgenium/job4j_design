create table banks(
    id serial primary key,
    name varchar(100)
);

create table bank_accounts(
	id serial primary key,
	account_number varchar(20),
	bank_id int references banks(id)
)