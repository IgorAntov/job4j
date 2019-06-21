drop table items;

create table items (
   id serial primary key not null,
   name varchar(2000),
   description varchar(100),
   date timestamp
);
