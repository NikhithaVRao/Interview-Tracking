alter table candidate
add interview_id varchar(20);

alter table interview drop column candidate_id ;

