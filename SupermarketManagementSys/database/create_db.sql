DROP DATABASE if exists db_supermarket;
CREATE DATABASE db_supermarket;

use db_supermarket;

CREATE TABLE tb_basicMessage (
id int unsigned not null primary key auto_increment,
name varchar(10) not null,
age int unsigned not null,
dept int unsigned not null,
positionId int unsigned
);

CREATE TABLE tb_contact (
id int unsigned not null primary key auto_increment,
hid int unsigned not null,
contact varchar(20),
officePhone varchar(30),
fax varchar(20),
email varchar(50),
homeAddress varchar(50),
foreign key(hid) references tb_basicMessage(id) on update cascade
);

CREATE TABLE tb_joinDept (
id int unsigned primary key auto_increment,
order_id varchar(50) not null,
depo_id int unsigned not null,
wareName varchar(40),
joinTime varchar(40),
weight float,
remark varchar(200)
);

CREATE TABLE tb_users (
id int unsigned primary key auto_increment,
userName varchar(20),
password varchar(20)
);

CREATE TABLE tb_provider (
id int unsigned not null primary key auto_increment,
cName varchar(20),
address varchar(40),
contactPerson varchar(50),
contactPhone varchar(50),
fax varchar(20),
postnumber varchar(20),
bankAccount varchar(50),
webAddress varchar(60),
email varchar(50),
remark varchar(200)
);

CREATE TABLE tb_position (
positionId int unsigned not null primary key auto_increment,
positionName varchar(100)
);