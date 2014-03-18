delete from Checking_Account;
delete from Account;
delete from Person;
delete from Postal;

--------

insert into Postal values(
'2800', 'Lyngby');



insert into Person values(
'020202-0202', 'Awesome', 'Bo', 'Bech', 'Vej 38', 56565656, 'bech@bo.dk', 'bech', '2800');

insert into account values (
'1234-1000', 'CheckingAccount', 0.2, '020202-0202');

insert into checking_account values ('1234-1000');