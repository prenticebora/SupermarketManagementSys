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


# SQL command to create the table: 
# Remember to correct VARCHAR column lengths to proper values 
# and add additional indexes for your own extensions.

# If you had prepaired CREATE TABLE SQL-statement before, 
# make sure that this automatically generated code is 
# compatible with your own code. If SQL code is incompatible,
# it is not possible to use these generated sources successfully.
# (Changing VARCHAR column lenghts will not break code.)

CREATE TABLE tb_user (
      id bigint AUTO_INCREMENT NOT NULL,
      userName varchar(255),
      password varchar(255),
PRIMARY KEY(id),
INDEX tb_user_id_INDEX (id));

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


# SQL command to create the table: 
# Remember to correct VARCHAR column lengths to proper values 
# and add additional indexes for your own extensions.

# If you had prepaired CREATE TABLE SQL-statement before, 
# make sure that this automatically generated code is 
# compatible with your own code. If SQL code is incompatible,
# it is not possible to use these generated sources successfully.
# (Changing VARCHAR column lenghts will not break code.)

CREATE TABLE tb_position (
      positionId bigint AUTO_INCREMENT NOT NULL,
      positionName varchar(255),
PRIMARY KEY(positionId),
INDEX tb_position_positionId_INDEX (positionId));