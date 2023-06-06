create table IF NOT EXISTS EMPLOYEE(
    id serial primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(255) not null
    );