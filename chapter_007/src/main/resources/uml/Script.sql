CREATE DATABASE tracker OWNER pcontrol;

create table role_rights (
  id serial primary key,
  name varchar(50) NOT NULL,
  role integer,
  description text
);

create table tracker_role (
  id serial primary key,
  description text,
  role_name varchar (50) NOT NULL
);

create table tracker_role_role_rights (
  tracker_role_id integer references tracker_role(id),
  role_rights_id integer references role_rights(id)
);

create table tracker_user (
  id serial primary key,
  description text,
  name varchar(50),
  role_name_id integer references tracker_role(id)
);

create table item_categories (
  id serial primary key,
  category varchar(50),
  description text
);


create table item_states (
  id serial primary key,
  state varchar(50)
);

create table tracker_item (
  id serial primary key,
  description text,
  name varchar(50),
  createDate timestamp,
  tracker_user_id integer references tracker_user(id),
  item_categories_id integer references item_categories(id),
  item_state_id integer references item_states(id)
);

create table item_comments (
  id serial primary key,
  comment text,
  createDate timestamp,
  tracker_item_id integer references tracker_item(id)
);

create table item_attaches (
  id serial primary key,
  attach varchar(100),
  createDate timestamp,
  tracker_item_id integer references tracker_item(id)
);

insert into role_rights(name, role, description) values('INSERT',1,'Allow to insert new item.');
insert into role_rights(name, role, description) values('EDIT',2,'Allow to edit items.');
insert into role_rights(name, role, description) values('DELETE',3,'Allow to delete items.');

insert into tracker_role(role_name, description) values('Administrators','All Rights');
insert into tracker_role(role_name, description) values('Role2','Only Insert is allowed');
insert into tracker_role(role_name, description) values('Role3','Only Insert and Edit are allowed');

insert into tracker_role_role_rights(tracker_role_id, role_rights_id)  values (1, 1);
insert into tracker_role_role_rights(tracker_role_id, role_rights_id)  values (1, 2);
insert into tracker_role_role_rights(tracker_role_id, role_rights_id)  values (1, 2);
insert into tracker_role_role_rights(tracker_role_id, role_rights_id)  values (2, 1);
insert into tracker_role_role_rights(tracker_role_id, role_rights_id)  values (3, 1);
insert into tracker_role_role_rights(tracker_role_id, role_rights_id)  values (3, 2);

insert into tracker_user(description, name, role_name_id) values ('Admin with all rights', 'Admin', 1);
insert into tracker_user(description, name, role_name_id) values ('User with Role1', 'User1', 2);
insert into tracker_user(description, name, role_name_id) values ('User with Role2', 'User2', 3);

insert into item_categories(description, category) values ('High priority', 'Items with high priority');
insert into item_categories(description, category) values ('Low priority', 'Items with low priority');
insert into item_categories(description, category) values ('middle priority', 'Items with middle priority');

insert into item_states(state) values ('Active');
insert into item_states(state) values ('Completed');
insert into item_states(state) values ('Suspended');

insert into tracker_item(description, name, createDate, tracker_user_id, item_categories_id, item_state_id)
values ('Items1 description', 'Item1', now(), 1, 1, 1);
insert into tracker_item(description, name, createDate, tracker_user_id, item_categories_id, item_state_id)
values ('Items2 description', 'Ite2', now(), 2, 1, 2);
insert into tracker_item(description, name, createDate, tracker_user_id, item_categories_id, item_state_id)
values ('Items1 description', 'Item1', now(), 3, 2, 3);

insert into item_comments(comment, createDate, tracker_item_id) VALUES ('Comment1 for Item1', now(), 1);
insert into item_comments(comment, createDate, tracker_item_id) VALUES ('Comment2 for Item1', now(), 1);
insert into item_comments(comment, createDate, tracker_item_id) VALUES ('Comment1 for Item2', now(), 2);

insert into item_attaches (attach, createDate, tracker_item_id) values ('C:/attached1foritem1.ext', now(), 1);
insert into item_attaches (attach, createDate, tracker_item_id) values ('C:/attached2foritem1.ext', now(), 1);
insert into item_attaches (attach, createDate, tracker_item_id) values ('C:/attached1foritem2.ext', now(), 2);

