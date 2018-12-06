drop table if exists `contact`;
create table contact(
    id int(6) unsigned auto_increment primary key,
    patient_id int(6) unsigned,
    phone_number varchar(20) not null,
    email varchar(100),
    foreign key (patient_id) references patient(id)
);

truncate contact;
insert into contact(phone_number, email, patient_id)
    select phone_number, null, id
    from patient;

alter table patient
drop column phone_number;