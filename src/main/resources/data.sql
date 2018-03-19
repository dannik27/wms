
INSERT INTO role (name) VALUES ('Администратор');

INSERT INTO permission (name) VALUES ('ROLES');
INSERT INTO permission (name) VALUES ('USERS');

INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 2);

insert into person ( name, last_name, middle_name, birth_date, email)
    values ( 'Семён', 'Кеков', 'Петрович', '1995-05-15', 'kekoff@mail.ru' );
insert into person ( name, last_name, middle_name, birth_date, email)
    values ( 'Демьян', 'Казенакский', 'Савельевич', '2005-11-11', 'azina@mail.ru' );
insert into person ( name, last_name, middle_name, birth_date, email)
    values ( 'Роман', 'Петров', 'Дмитриевич', '2006-11-10', 'lollo@mail.ru' );
insert into person ( name, last_name, middle_name, birth_date, email)
    values ( 'Иван', 'Серябкин', 'Анатолиевич', '1945-11-10', 'ivan@mail.ru' );

INSERT INTO worker (id_person) VALUES (1);
INSERT INTO worker (id_person) VALUES (2);

INSERT INTO task_type (name) VALUES ('Поступление');
INSERT INTO task_type (name) VALUES ('Выборка');

insert into product (description, name, volume) VALUES ('Фигня какая-то', 'штуцер 50х3', 1);
insert into product (description, name, volume) VALUES ('Фигня какая-то', 'Нипель а5', 2);
insert into product (description, name, volume) VALUES ('Фигня какая-то', 'Игрушка "Мишка"', 1);

INSERT INTO company ( description, email, inn, kpp, okpo, name, phone, id_person)
    VALUES ( 'Наша компания', 'nash@mail.ru', 'inn', 'kpp', 'okpo','ООО Кекес Макекес', '88005489647', 1);
INSERT INTO company ( description, email, inn, kpp, okpo, name, phone, id_person)
    VALUES ( 'Кировские колбасы', 'kirkol@mail.ru', 'inn', 'kpp', 'okpo','Кировские колбасы', '89054326523', 3);
INSERT INTO company ( description, email, inn, kpp, okpo, name, phone, id_person)
    VALUES ( 'Грузоперевозки', 'gruzzo@mail.ru', 'inn', 'kpp', 'okpo','Грузоперевозкин', '89054326523', 4);


INSERT INTO customer (id_company) VALUES (1);
INSERT INTO customer (id_company) VALUES (2);

INSERT INTO transport_company (id_company) VALUES (3);

INSERT INTO storehouse (name, id_customer) VALUES ('Склад материалов', 1);
INSERT INTO storehouse (name, id_customer) VALUES ('Склад стройматериалов', 2);

INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 40, 'A1');
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 20, 'A2');
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 20, 'A3');


INSERT INTO request (date_begin, id_worker, id_customer, id_storehouse_from, id_storehouse_to)
    VALUES ('2018-05-03', 2, null, 2, 1);

INSERT INTO request_item (count, id_product, id_request) VALUES (40, 1, 1);
INSERT INTO request_item (count, id_product, id_request) VALUES (15, 2, 1);


INSERT into task (id_task_type, id_worker, time_begin, time_end)
    VALUES (1, 2, '2018-03-01', '2018-03-05');
INSERT into task (id_task_type, id_worker, time_begin, time_end)
    VALUES (2, 1, '2018-03-05', null);

INSERT INTO task_item (id_product, id_task, count) VALUES (2, 1, 25);
INSERT INTO task_item (id_product, id_task, count) VALUES (3, 1, 10);
INSERT INTO task_item (id_product, id_task, count) VALUES (3, 2, -10);



INSERT INTO distribution (id_storehouse_cell, id_task_item, count) VALUES (1, 1, 20);
INSERT INTO distribution (id_storehouse_cell, id_task_item, count) VALUES (2, 1, 5);
INSERT INTO distribution (id_storehouse_cell, id_task_item, count) VALUES (3, 2, 10);
INSERT INTO distribution (id_storehouse_cell, id_task_item, count) VALUES (3, 3, -10);

update storehouse_cell set id_product = 2 where id = 1;
update storehouse_cell set id_product = 2 where id = 2;


