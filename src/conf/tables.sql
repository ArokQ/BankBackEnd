drop table Person;
drop table Postal;
drop table Account;
drop table Transfer;
drop table CheckingAccount;

---------------
create table Person(
cpr int primary key,
title varchar(40) not null,
firstName varchar(40) not null,
lastName varchar(40) not null,
street varchar(100) not null,
phone int not null,
email varchar(80),
password varchar(80));


create table Postal(
code int primary key,
district varchar(40));

-- FOREIGN KEYS!???????????
-----------------

create table Account(
accountNumber int primary key,
balance float not null,
interest float not null);

create table Transfer(
amount float primary key,
transferDate date not null);

create table CheckingAccount(
cpr int not null,
interest float not null);


------------------