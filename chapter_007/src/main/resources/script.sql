CREATE TABLE company
(
  id integer NOT NULL,
  name character varying,
  CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
  id integer NOT NULL,
  name character varying,
  company_id integer,
  CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company values (1, 'company1');
insert into company values (2, 'company2');
insert into company values (3, 'company3');
insert into company values (4, 'company4');
insert into company values (5, 'company5');

insert into person values (1, 'person1comp1', 1);
insert into person values (2, 'person2comp1', 1);
insert into person values (3, 'person3comp1', 1);
insert into person values (4, 'person4comp1', 1);

insert into person values (5, 'person1comp2', 2);
insert into person values (6, 'person2comp2', 2);

insert into person values (7, 'person1comp3', 3);
insert into person values (8, 'person2comp3', 3);
insert into person values (9, 'person3comp3', 3);
insert into person values (10, 'person4comp3', 3);
insert into person values (11, 'person5comp3', 3);

insert into person values (12, 'person1comp4', 4);
insert into person values (13, 'person2comp4', 4);

insert into person values (14, 'person1comp5', 5);
insert into person values (15, 'person2comp5', 5);
insert into person values (16, 'person3comp5', 5);
insert into person values (17, 'person4comp5', 5);

-- 1) Retrieve in a single query:
-- - names of all persons that are NOT in the company with id = 5
-- - company name for each person
select p.name as person, c.name company_name from person p inner join company c on p.company_id = c.id where p.id != 5;
-- 2) Select the name of the company with the maximum number of persons + number of persons in this company
select max(a.maxcount) from (select count(p.id) as maxcount, c.name company_name from person p inner join company c on p.company_id = c.id group by c.name) as a;
