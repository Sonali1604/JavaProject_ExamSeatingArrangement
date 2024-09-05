CREATE DATABASE project;
use project;
CREATE TABLE seating_arrangement (
    roll_no VARCHAR(10) PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    class VARCHAR(10) NOT NULL,
    seat_number VARCHAR(10) UNIQUE
);
truncate table seating_arrangement;
select * from seating_arrangement;
select * from csitlist;
CREATE TABLE csitlist (
    roll_no VARCHAR(10) PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    class VARCHAR(10) NOT NULL
);
