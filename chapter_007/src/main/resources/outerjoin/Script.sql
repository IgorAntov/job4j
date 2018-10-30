create table car_body (
  id serial primary key,
  name varchar(50)
);

create table engine (
  id serial primary key,
  name varchar(50)
);

create table transmission (
  id serial primary key,
  name varchar(50)
);

create table car (
  id serial primary key,
  name varchar(50),
  car_body_id integer references car_body(id),
  engine_id integer references engine(id),
  transmission_id integer references transmission(id)
);

insert into car_body (name) values ('hatchback');
insert into car_body (name) values ('pickup');
insert into car_body (name) values ('sedan');
insert into car_body (name) values ('minibus');

insert into engine (name) values ('diesel');
insert into engine (name) values ('electric');
insert into engine (name) values ('petrol');
insert into engine (name) values ('steam');

insert into transmission (name) values ('auto');
insert into transmission (name) values ('vsd');
insert into transmission (name) values ('manual');

insert into car(name, car_body_id, engine_id, transmission_id) VALUES ('kia', 1, 3, 1);
insert into car(name, car_body_id, engine_id, transmission_id) VALUES ('bmv', 3, 3, 3);
insert into car(name, car_body_id, engine_id, transmission_id) VALUES ('ford', 2, 1, 3);
insert into car(name, car_body_id, engine_id, transmission_id) VALUES ('lada', 3, 3, 3);
insert into car(name, car_body_id, engine_id, transmission_id) VALUES ('toyota', 3, 2, 1);

--1. Вывести список всех машин и все привязанные к ним детали.
select c.name, cb.name, e.name, t.name from car c left outer join car_body cb on c.car_body_id = cb.id left outer join engine e on c.engine_id = e.id left outer join transmission t on c.transmission_id = t.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select cb.name as "car_body" from car_body cb full outer join car c on cb.id = c.car_body_id where c.name isnull;
select e.name as "engine", c.name from engine e left outer join car c on e.id = c.engine_id where c.name isnull;
select t.name as "transmission", c.name from transmission t left outer join car c on t.id = c.transmission_id where c.name isnull;
