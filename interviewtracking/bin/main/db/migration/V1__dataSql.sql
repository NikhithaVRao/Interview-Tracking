create table hibernate_sequence
(
next_val bigint
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

insert into hibernate_sequence values (1);

create table candidate
(
id INT NOT NULL AUTO_INCREMENT,
interview_id varchar(20),
name varchar(20) NOT NULL,
address varchar(20),
date_of_birth date,
qualification varchar(20) NOT NULL,
total_experience int,
notice_period int,
salary_expectation float,
able_to_relocate boolean,
current_salary float,
gender varchar(20),
phone_number varchar(20) NOT NULL,
email varchar(100) NOT NULL,
deleted boolean,
shortListed boolean,
create_timestamp DATETIME,
update_timestamp DATETIME,
unique_id varchar(20) NOT NULL,
previous_company varchar(20),
attempt_count int,
carrier_start_date varchar(20),
applicant_type varchar(20),
post_applied varchar(20),
referal_id varchar(100),
event varchar(20) not null,
final_result varchar(10),
primary key(id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;



create table hr_panel
(
id int not null auto_increment,
employee_id varchar(20),
name varchar(20),
designation varchar(20),
create_timestamp DATETIME,
update_timestamp DATETIME,
email varchar(20),
deleted  boolean ,
primary key(id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

create table technical_panel
(
id int not null auto_increment,
employee_id varchar(20),
name varchar(20),
expertise varchar(20),
create_timestamp DATETIME,
update_timestamp DATETIME,
email varchar(20),
deleted boolean,
available_morning boolean,
available_afternoon boolean,
available_evening boolean,
primary key(id)
);

create table skills
(
sid int not null auto_increment,
candidate_id int,
skills_name varchar(20),
experience int,
is_deleted boolean,
primary key(sid)
);

create table interview
(
id int not null auto_increment,
interview_id varchar(30),
assignee_id int,
status varchar(20),
round varchar(20),
comments varchar(20),
final_result varchar(20),
create_timestamp DATETIME,
update_timestamp DATETIME,
deleted boolean ,
employee_id int,
interview_date date,
primary key(id)
);



