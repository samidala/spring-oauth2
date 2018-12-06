create table address(
    id int(6) unsigned auto_increment primary key,
    patient_id int(6) unsigned,
    houseNo varchar(100),
    streetNo varchar(100),
    name varchar(100),
    state varchar(50),
    city varchar(50),
    country varchar(50),
    zipcode varchar(50),
    foreign key (patient_id) references patient(id)
);