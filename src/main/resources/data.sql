

insert into person ( name, last_name, middle_name, birth_date, email)
    values ( 'Семён', 'Кеков', 'Петрович', '1995-05-15', 'kekoff@mail.ru' );
insert into person ( name, last_name, middle_name, birth_date, email)
    values ( 'Демьян', 'Казенакский', 'Савельевич', '2005-11-11', 'azina@mail.ru' );
insert into person ( name, last_name, middle_name, birth_date, email)
    values ( 'Роман', 'Петров', 'Дмитриевич', '2006-11-10', 'lollo@mail.ru' );

INSERT INTO worker (id_person) VALUES (1);
INSERT INTO worker (id_person) VALUES (2);

INSERT INTO task_type (name) VALUES ('Поступление');
INSERT INTO task_type (name) VALUES ('Выборка');

insert into product (description, name, volume) VALUES ('Фигня какая-то', 'штуцер 50х3', 1);
insert into product (description, name, volume) VALUES ('Фигня какая-то', 'Нипель а5', 1);
insert into product (description, name, volume) VALUES ('Фигня какая-то', 'Игрушка "Мишка"', 1);

INSERT INTO storehouse (name) VALUES ('Склад материалов');

INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 25, 'A1');
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 20, 'A2');
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 20, 'A3');

INSERT INTO company ( description, email, inn, kpp, okpo, name, phone, id_person)
        VALUES ( 'Кировские колбасы', 'kirkol@mail.ru', 'inn', 'kpp', 'okpo','Кировские колбасы', '89054326523', 3);



INSERT into task (id_task_type, id_worker, time_begin, time_end)
    VALUES (1, 2, '2018-03-01', '2018-03-05');
INSERT into task (id_task_type, id_worker, time_begin, time_end)
    VALUES (2, 1, '2018-03-05', null);

INSERT INTO task_item (id_product, id_task, count) VALUES (2, 1, 40);
INSERT INTO task_item (id_product, id_task, count) VALUES (3, 1, 15);
INSERT INTO task_item (id_product, id_task, count) VALUES (3, 2, -15);

INSERT INTO distribution (id_storehouse_cell, id_task_item, count) VALUES (1, 1, 25);
INSERT INTO distribution (id_storehouse_cell, id_task_item, count) VALUES (2, 1, 15);
INSERT INTO distribution (id_storehouse_cell, id_task_item, count) VALUES (3, 2, 15);
INSERT INTO distribution (id_storehouse_cell, id_task_item, count) VALUES (3, 3, -15);

