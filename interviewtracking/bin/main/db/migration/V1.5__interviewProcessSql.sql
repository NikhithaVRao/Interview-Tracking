
create table interview
(
id int not null auto_increment,
assigneeId int,
status boolean,
comments varchar(20),
round varchar(20),
finalResult varchar(20),
candidateId int,
create_timestamp timestamp,
update_timestamp timestamp,
isDeleted boolean ,
primary key(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;




