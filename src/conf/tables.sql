drop table Transfer;
drop table Account;
drop table Person;
drop table Postal;



---------------
create table Postal(
code varchar(8) primary key,
district varchar(80) not null);


create table Person(
cpr varchar(12) primary key,
title varchar(40) not null,
firstName varchar(40) not null,
lastName varchar(40) not null,
street varchar(100) not null,
phone int not null,
email varchar(80),
password varchar(80),
postal_code varchar(8) not null references Postal(code));


-----------------

create table Account(
accountNumber int primary key,
balance float not null,
interest float not null,
person_cpr varchar(12) not null references Person(cpr));

create table Transfer(
amount float primary key,
transferDate date not null,
targetAccount varchar(10) not null,
account_accountNumber int not null references Account(accountNumber));



------------------