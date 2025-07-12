create table genres(
	id serial primary key,
	name varchar(255)
);

create table games(
	id serial primary key,
	name varchar(255),
	release_date date,
	genre_id int references genres(id)
);

insert into genres(name) values ('RPG');
insert into genres(name) values ('TBS');
insert into genres(name) values ('Action');

insert into games(name, release_date, genre_id) values ('Fallout 2', '1998-10-29', 1);
insert into games(name, release_date, genre_id) values ('Fallout 3', '2008-10-28', 1);
insert into games(name, release_date, genre_id) values ('HoMM III', '1999-03-03', 2);
insert into games(name, release_date, genre_id) values ('HoMM IV', '2002-03-28', 2);
insert into games(name, release_date, genre_id) values ('Half-Life', '1998-11-19', 3);
insert into games(name, release_date, genre_id) values ('Half-Life 2', '2004-11-16', 3);

select gm.name, gm.release_date, gn.name
from games as gm join genres as gn on gm.genre_id = gn.id;

select gn.name, gm.release_date, gm.name
from genres as gn join games as gm on gn.id = gm.genre_id;

select gn.name as "Жанр", gm.name as "Название", gm.release_date as "Дата релиза"
from genres gn join games gm on gn.id = gm.genre_id;