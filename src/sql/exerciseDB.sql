create database exerciseDB;

use exerciseDB;

create table administrators
(
    id       int auto_increment primary key,##用户id
    name     varchar(50),##用户名称
    password varchar(16)##密码
);
drop table administrators;
insert into administrators(name, password)
VALUES ('admin', '123456');

select *
from administrators;

create table exerciseLibrary
(
    id           int auto_increment primary key,##编号
    types        varchar(20),##题目的类型
    correct      int,##提名都正确数量
    topic        text,##题目
    toOption     varchar(50),##选项
    answer       text,## 答案(解析)
    total        long,##该题被答的次数
    numberErrors long##被答错的次数
);
drop table exerciseLibrary;
insert into exerciseLibrary( types, correct, topic, toOption, answer, total, numberErrors)
VALUES ('单选题',1,'1+1=(  )','2<>BCT<>1<>BCT<>3<>BCT<>4',
        '1+1!=1<>BCT<>1+1!=3<>BCT<>1+1!=4',0,0);

select id, types, correct, topic, toOption, answer, total, numberErrors
from exerciseLibrary;
