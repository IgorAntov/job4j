create table type (
  id serial primary key,
  name varchar(100)
);

create table product (
  id serial primary key,
  name varchar(100),
  expired_date timestamp,
  price integer,
  type_id integer references type(id)
);

insert into type(name) values ('СЫР');
insert into type(name) values ('КОЛБАСА');
insert into type(name) values ('ПИВО');
insert into type(name) values ('МОЛОКО');

insert into product(name, expired_date, price, type_id)  values ('Моцарелла', '2018-10-30', 100, 1);
insert into product(name, expired_date, price, type_id)  values ('Бри', '2018-11-5', 150, 1);
insert into product(name, expired_date, price, type_id)  values ('Гауда', '2018-12-10', 200, 1);

insert into product(name, expired_date, price, type_id)  values ('Докторская', '2018-11-2', 300, 2);
insert into product(name, expired_date, price, type_id)  values ('Сервелат', '2018-11-7', 350, 2);
insert into product(name, expired_date, price, type_id)  values ('Сырокопченая', '2018-12-12', 400, 2);
insert into product(name, expired_date, price, type_id)  values ('Московская', '2018-12-18', 450, 2);

insert into product(name, expired_date, price, type_id)  values ('Клинское', '2018-11-4', 100, 3);
insert into product(name, expired_date, price, type_id)  values ('Жигулевское', '2018-11-12', 150, 3);
insert into product(name, expired_date, price, type_id)  values ('Козел', '2018-12-15', 200, 3);
insert into product(name, expired_date, price, type_id)  values ('Балтика', '2018-11-19', 250, 3);
insert into product(name, expired_date, price, type_id)  values ('Гиннесс', '2018-11-23', 170, 3);
insert into product(name, expired_date, price, type_id)  values ('Хугарден', '2019-11-23', 200, 3);
insert into product(name, expired_date, price, type_id)  values ('Миллер', '2019-11-11', 90, 3);
insert into product(name, expired_date, price, type_id)  values ('Старопрамен', '2019-01-10', 110, 3);
insert into product(name, expired_date, price, type_id)  values ('Старый мельник', '2019-11-23', 150, 3);
insert into product(name, expired_date, price, type_id)  values ('Туборг', '2019-01-14', 80, 3);
insert into product(name, expired_date, price, type_id)  values ('Эффес', '2019-02-20', 125, 3);

insert into product(name, expired_date, price, type_id)  values ('Молоко', '2018-12-1', 50, 4);
insert into product(name, expired_date, price, type_id)  values ('Мороженое Пломбир', '2018-12-3', 100, 4);
insert into product(name, expired_date, price, type_id)  values ('Мороженое Сливочное', '2018-12-3', 150, 4);
insert into product(name, expired_date, price, type_id)  values ('Творог', '2018-11-25', 200, 4);

--1. Написать запрос получение всех продуктов с типом "СЫР"
select name from product where type_id = 1;
--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select name from product where name like '%Мороженое%';
--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select name from product where EXTRACT(MONTH FROM expired_date) = EXTRACT(MONTH FROM now())+1;
--4. Написать запрос, который выводит самый дорогой продукт.
select name from product  where price = (select max(price) from product);
--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select t.name, (select count(p.id) from product p where p.type_id = t.id) from type t;
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.name from product p where p.type_id in ((select t.id from type t where t.name = 'СЫР'), (select t.id from type t where t.name = 'МОЛОКО'));
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name from type t where (select count(p.id) from product p where p.type_id = t.id) < 10;
--8. Вывести все продукты и их тип.
select p.name, t.name from product p inner join type t on p.type_id = t.id;

