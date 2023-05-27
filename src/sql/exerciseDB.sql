create database exerciseDB;

use exerciseDB;

create table administrators(
    id int auto_increment primary key ,##用户id
    name varchar(50),##用户名称
    password varchar(16) ##密码
);
drop table administrators;
insert into administrators(name, password) VALUES('admin','123456');

select * from administrators;

