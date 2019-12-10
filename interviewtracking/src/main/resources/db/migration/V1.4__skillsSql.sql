create table skills
(
sid int not null auto_increment,
candidateId int,
skillName varchar(20),
experience int,
isDeleted boolean,
primary key(sid)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;


