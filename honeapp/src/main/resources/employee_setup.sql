CREATE DATABASE IF NOT EXISTS lms_db;
USE lms_db;

DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    designation VARCHAR(50),
    salary DECIMAL(10,2),
    experience INT,
    city VARCHAR(50),
    age INT,
    email VARCHAR(100),
    joining_date DATE
);

INSERT INTO employee (name, department, designation, salary, experience, city, age, email, joining_date) VALUES
('Ravi Kumar', 'IT', 'Software Engineer', 65000, 3, 'Hyderabad', 26, 'ravi@gmail.com', '2023-06-15'),
('Priya Sharma', 'IT', 'Senior Software Engineer', 85000, 6, 'Bengaluru', 30, 'priya@gmail.com', '2020-04-10'),
('Amit Verma', 'Finance', 'Financial Analyst', 72000, 5, 'Mumbai', 29, 'amit@gmail.com', '2021-07-20'),
('Sneha Reddy', 'HR', 'HR Executive', 55000, 4, 'Hyderabad', 27, 'sneha@gmail.com', '2022-01-15'),
('Rahul Singh', 'IT', 'Tech Lead', 110000, 10, 'Pune', 35, 'rahul@gmail.com', '2016-03-12'),
('Anita Rao', 'Marketing', 'Marketing Manager', 90000, 8, 'Chennai', 33, 'anita@gmail.com', '2018-09-01'),
('Vijay Kumar', 'Sales', 'Sales Executive', 48000, 2, 'Delhi', 24, 'vijay@gmail.com', '2024-01-10'),
('Kavya Nair', 'IT', 'Software Engineer', 70000, 4, 'Kochi', 28, 'kavya@gmail.com', '2022-06-18'),
('Suresh Babu', 'Finance', 'Senior Accountant', 78000, 7, 'Bengaluru', 32, 'suresh@gmail.com', '2019-05-25'),
('Meena Iyer', 'HR', 'HR Manager', 95000, 9, 'Chennai', 36, 'meena@gmail.com', '2017-11-05'),
('Arjun Patel', 'IT', 'DevOps Engineer', 88000, 6, 'Ahmedabad', 31, 'arjun@gmail.com', '2020-08-14'),
('Divya Menon', 'Sales', 'Sales Manager', 82000, 7, 'Kochi', 32, 'divya@gmail.com', '2019-02-20'),
('Kiran Das', 'Marketing', 'Marketing Executive', 58000, 3, 'Kolkata', 26, 'kiran@gmail.com', '2023-09-11'),
('Pooja Shah', 'Finance', 'Finance Manager', 105000, 11, 'Mumbai', 38, 'pooja@gmail.com', '2015-01-19'),
('Manoj Kumar', 'IT', 'Architect', 125000, 14, 'Bengaluru', 40, 'manoj@gmail.com', '2012-07-23'),
('Neha Gupta', 'HR', 'HR Executive', 60000, 3, 'Delhi', 25, 'neha@gmail.com', '2023-03-17'),
('Vikas Rao', 'IT', 'QA Engineer', 68000, 5, 'Pune', 29, 'vikas@gmail.com', '2021-10-12'),
('Swathi Reddy', 'Finance', 'Accountant', 62000, 3, 'Hyderabad', 27, 'swathi@gmail.com', '2023-05-08'),
('Ramesh Naik', 'Sales', 'Sales Executive', 52000, 4, 'Visakhapatnam', 28, 'ramesh@gmail.com', '2022-08-16'),
('Lakshmi Devi', 'Marketing', 'Marketing Manager', 98000, 10, 'Chennai', 37, 'lakshmi@gmail.com', '2016-12-01');
