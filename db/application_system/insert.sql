insert into roles(name) values 
	('Администратор'), 
	('Пользователь');

insert into rules(name) values
	('Полные права'), 
	('Базовые права'), 
	('Создание/редактирование заявок');
								
insert into roles_rules(role_id, rule_id) values
	(1, 1),
	(2, 2),
	(2, 3);

insert into users(name, role_id) values
	('Владимир Сидоров', 1),
	('Иван Петров', 2);

insert into categories(name) values 
	('Низкая'), 
	('Средняя'), 
	('Высокая');

insert into states(name) values
	('Не принята'), 
	('В работе'), 
	('Выполнена');

insert into items(name, user_id, category_id, state_id) values
	('Заполнить картридж в принтере.', 2, 2, 1);

insert into comments(name, item_id) values
	('Уточните какой конкретно принтер нужен.', 1);

insert into attachs(name, item_id) values
	('Скрин бледно печатает.jpg', 1);