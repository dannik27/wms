
-- operation_type
-- 0 - IN
-- 1 - OUT
-- 2 - IN_OUT

-- task_status
-- 0 - NOT_READY
-- 1 - READY
-- 2 - IN_WORK
-- 3 - DONE

INSERT INTO role (name) VALUES ('Администратор'); --1
INSERT INTO role (name) VALUES ('Никто');         --2

INSERT INTO permission (name) VALUES ('ROLES'); --1
INSERT INTO permission (name) VALUES ('USERS'); --2

INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1); -- 1
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 2); -- 2

insert into person ( name, last_name, middle_name, birth_date, email)
    values ( 'Семён', 'Кеков', 'Петрович', '1995-05-15', 'kekoff@mail.ru' ); -- 1
insert into person ( name, last_name, middle_name, birth_date, email)
    values ( 'Демьян', 'Казенакский', 'Савельевич', '2005-11-11', 'azina@mail.ru' ); -- 2
insert into person ( name, last_name, middle_name, birth_date, email)
    values ( 'Роман', 'Петров', 'Дмитриевич', '2006-11-10', 'lollo@mail.ru' ); -- 3
insert into person ( name, last_name, middle_name, birth_date, email)
    values ( 'Иван', 'Серябкин', 'Анатолиевич', '1945-11-10', 'ivan@mail.ru' ); -- 4

INSERT INTO worker (id_person, id_role, date_hired, login, "password") VALUES (1, 2, '1995-05-15', 1, 1); -- 1
INSERT INTO worker (id_person, id_role, date_hired, login, "password") VALUES (2, 2, '2002-06-18', 2, 2); -- 2
INSERT INTO worker (id_person, id_role, date_hired, login, "password") VALUES (3, 2, '2002-06-18', 3, 3); -- 3


insert into product (description, name, volume) VALUES ('Фигня какая-то', 'штуцер 50х3', 2); -- 1
insert into product (description, name, volume) VALUES ('Фигня какая-то', 'Нипель а5', 1); -- 2
insert into product (description, name, volume) VALUES ('Фигня какая-то', 'Игрушка "Мишка"', 1); -- 3

INSERT INTO company ( description, email, inn, kpp, okpo, name, phone, id_person)
    VALUES ( 'Наша компания', 'nash@mail.ru', 'inn', 'kpp', 'okpo','ООО Кекес Макекес', '88005489647', 1); -- 1
INSERT INTO company ( description, email, inn, kpp, okpo, name, phone, id_person)
    VALUES ( 'Кировские колбасы', 'kirkol@mail.ru', 'inn', 'kpp', 'okpo','Кировские колбасы', '89054326523', 3); -- 2
INSERT INTO company ( description, email, inn, kpp, okpo, name, phone, id_person)
    VALUES ( 'Грузоперевозки', 'gruzzo@mail.ru', 'inn', 'kpp', 'okpo','Грузоперевозкин', '89054326523', 4); -- 3
INSERT INTO company ( description, email, inn, kpp, okpo, name, phone, id_person)
    VALUES ( 'Наша компания', 'nasha@mail.ru', 'inn', 'kpp', 'okpo','ООО Наша Компания', '88005553535', 1); -- 4 Наша


INSERT INTO customer (id_company) VALUES (1); -- 1
INSERT INTO customer (id_company) VALUES (2); -- 2
INSERT INTO customer (id_company) VALUES (4); -- 3 Мы

INSERT INTO transport_company (id_company) VALUES (3); -- 1

INSERT INTO storehouse (name, id_customer, address) VALUES ('Склад материалов', 3, 'Где-то на белом свете'); -- 1
INSERT INTO storehouse (name, id_customer, address) VALUES ('Склад стройматериалов', 3, 'Рядом с первым'); -- 2

update worker set id_storehouse = 1 where id = 1;
update worker set id_storehouse = 2 where id = 2;
update worker set id_storehouse = 1 where id = 3;

INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 20, 'A1'); -- 1
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 20, 'A2'); -- 2
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 20, 'A3'); -- 3
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 20, 'A4'); -- 4
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 30, 'AA1'); -- 5
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 30, 'AA2'); -- 6
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 30, 'AA3'); -- 7
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 30, 'AA4'); -- 8
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (1, 30, 'AA5'); -- 9

INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (2, 50, 'B1'); -- 10
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (2, 50, 'B2'); -- 11
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (2, 50, 'B3'); -- 12
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (2, 10, 'BA1'); -- 13
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (2, 10, 'BA2'); -- 14
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (2, 10, 'BA3'); -- 15
INSERT INTO storehouse_cell (id_storehouse, capacity, name) VALUES (2, 40, 'BB1'); -- 16

-- Поступление на склад

INSERT INTO request (date_begin, id_worker, id_customer, id_storehouse_from, id_storehouse_to, operation_type)
    VALUES ('2018-03-05', 2, 1, null, 1, 0);

INSERT INTO request_item (count, id_product, id_request) VALUES (25, 1, 1);
INSERT INTO request_item (count, id_product, id_request) VALUES (10, 2, 1);

insert into waybill (info, id_transport_company) values ('транспортная накладная', 1);

insert into packing_list (info) values ('товарная накладная');

insert into transportation (id_request, id_packing_list, id_waybill, gross_weight, date_shipped, date_received)
    values (1, 1, 1, 50, '2018-03-20 14:50', null);

INSERT into task (id_worker, time_begin, time_end, operation_type, task_status)
    VALUES (1, '2018-03-21 9:30', '2018-03-21 10:10', 0, 3); -- 1

INSERT INTO task_item (id_product, id_task, count) VALUES (1, 1, 15);
INSERT INTO task_item (id_product, id_task, count) VALUES (2, 1, 20);


INSERT INTO distribution (id_storehouse_cell, id_task_item, count, done) VALUES (1, 1, 10, true);
INSERT INTO distribution (id_storehouse_cell, id_task_item, count, done) VALUES (2, 1, 5, true);
INSERT INTO distribution (id_storehouse_cell, id_task_item, count, done) VALUES (3, 2, 20, true);

update storehouse_cell set id_product = 1 where id = 1;
update storehouse_cell set id_product = 1 where id = 2;
update storehouse_cell set id_product = 2 where id = 3;

-- Перенос со склада на склад

INSERT INTO request (date_begin, id_worker, id_customer, id_storehouse_from, id_storehouse_to, operation_type)
    VALUES ('2018-03-10', 2, null, 1, 2, 2);

INSERT INTO request_item (count, id_product, id_request) VALUES (10, 1, 2);

insert into waybill (info, id_transport_company) values ('транспортная накладная 2', 1);

insert into packing_list (info) values ('товарная накладная 2');

insert into transportation (id_request, id_packing_list, id_waybill, gross_weight, date_shipped, date_received)
    values (2, 2, 2, 22, null, null);

INSERT into task (id_worker, time_begin, time_end, operation_type, task_status)
    VALUES (3, null, '2018-03-22', 1, 3); -- 2
INSERT into task (id_worker, time_begin, time_end, operation_type, task_status)
    VALUES (2, null, '2018-03-23', 0, 3); -- 3

INSERT INTO task_item (id_product, id_task, count) VALUES (1, 2, 10); -- 3

INSERT INTO task_item (id_product, id_task, count) VALUES (1, 3, 10); -- 4

INSERT INTO distribution (id_storehouse_cell, id_task_item, count, done) VALUES (1, 3, -10, true);
INSERT INTO distribution (id_storehouse_cell, id_task_item, count, done) VALUES (4, 4, 10, true);

update storehouse_cell set id_product = 1 where id = 4;

-- Отгрузка со склада

INSERT INTO request (date_begin, id_worker, id_customer, id_storehouse_from, id_storehouse_to, operation_type)
    VALUES ('2018-03-11', 2, 2, 1, null, 1);

INSERT INTO request_item (count, id_product, id_request) VALUES (7, 1, 3);

insert into waybill (info, id_transport_company) values ('транспортная накладная 3', 1);

insert into packing_list (info) values ('товарная накладная 3');

insert into transportation (id_request, id_packing_list, id_waybill, gross_weight, date_shipped, date_received)
    values (3, 3, 3, 30, null, null);

INSERT into task (id_worker, time_begin, time_end, operation_type, task_status)
    VALUES (2, null, '2018-03-24', 1, 3); -- 4

INSERT INTO task_item (id_product, id_task, count) VALUES (1, 4, 7); -- 5

INSERT INTO distribution (id_storehouse_cell, id_task_item, count, done) VALUES (4, 5, -7, true);