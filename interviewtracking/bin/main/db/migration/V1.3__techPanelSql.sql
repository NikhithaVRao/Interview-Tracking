create table technicalPanel
(
id int not null auto_increment,
name varchar(20),
expertise varchar(20),
create_timestamp timestamp,
update_timestamp timestamp,
email varchar(20),
isDeleted boolean,
primary key(id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;
