create table bank_classifier(
	id serial primary key,
	bank_identification_code varchar(9)	
);

create table banks(
    id serial primary key,
    name varchar(100),
	bank_classifier_id int references bank_classifier(id) unique
);