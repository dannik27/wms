

insert into person (id, name, last_name, middle_name, birth_date, email)
    values (1, 'Семён', 'Кеков', 'Петрович', '1995-05-15', 'kekoff@mail.ru' );
insert into person (id, name, last_name, middle_name, birth_date, email)
    values (2, 'Демьян', 'Казенакский', 'Савельевич', '2005-11-11', 'azina@mail.ru' );
insert into person (id, name, last_name, middle_name, birth_date, email)
    values (3, 'Роман', 'Петров', 'Дмитриевич', '2006-11-10', 'lollo@mail.ru' );

INSERT INTO worker (id, id_person) VALUES (1, 1);
INSERT INTO worker (id, id_person) VALUES (2, 2);

INSERT INTO task_type (id, name) VALUES (1, 'Поступление');
INSERT INTO task_type (id, name) VALUES (2, 'Выборка');

insert into product (id, description, name, volume) VALUES (1, 'Фигня какая-то', 'штуцер 50х3', 1);
insert into product (id, description, name, volume) VALUES (2, 'Фигня какая-то', 'Нипель а5', 1);
insert into product (id, description, name, volume) VALUES (3, 'Фигня какая-то', 'Игрушка "Мишка"', 1);

INSERT INTO storehouse (id, name) VALUES (1, 'Склад материалов');

INSERT INTO storehouse_cell (id, id_storehouse, capacity, name) VALUES (1, 1, 25, 'A1');
INSERT INTO storehouse_cell (id, id_storehouse, capacity, name) VALUES (2, 1, 20, 'A2');
INSERT INTO storehouse_cell (id, id_storehouse, capacity, name) VALUES (3, 1, 20, 'A3');

INSERT INTO company (id, description, email, inn, kpp, name, phone, id_person)
        VALUES (1, 'Кировские колбасы', 'kirkol@mail.ru', 'inn', 'kpp', 'Кировские колбасы', '89054326523', 3);



INSERT into task (id, id_task_type, id_worker, time_begin, time_end)
    VALUES (1, 1, 2, '2018-03-01', '2018-03-05');
INSERT into task (id, id_task_type, id_worker, time_begin, time_end)
    VALUES (2, 2, 1, '2018-03-05', null);

INSERT INTO task_item (id, id_product, id_task, count) VALUES (1, 2, 1, 40);
INSERT INTO task_item (id, id_product, id_task, count) VALUES (2, 3, 1, 15);
INSERT INTO task_item (id, id_product, id_task, count) VALUES (3, 3, 2, -15);

INSERT INTO distribution (id, id_storehouse_cell, id_task_item, count) VALUES (1, 1, 1, 25);
INSERT INTO distribution (id, id_storehouse_cell, id_task_item, count) VALUES (2, 2, 1, 15);
INSERT INTO distribution (id, id_storehouse_cell, id_task_item, count) VALUES (3, 3, 2, 15);
INSERT INTO distribution (id, id_storehouse_cell, id_task_item, count) VALUES (4, 3, 3, -15);