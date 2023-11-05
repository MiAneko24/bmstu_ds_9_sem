CREATE ROLE program WITH PASSWORD 'test';
GRANT ALL PRIVILEGES ON DATABASE postgres TO program;
ALTER ROLE program WITH LOGIN;

create table person(
    id int primary key,
    name text not null,
    age int,
    address text,
    work text
);
