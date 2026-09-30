-- Project 4: Hibernate One-to-One Mapping
-- Run this script in MySQL before running com.training.App.

DROP DATABASE IF EXISTS hibernate_one_to_one_db;
CREATE DATABASE hibernate_one_to_one_db;
USE hibernate_one_to_one_db;

CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    salary DECIMAL(10,2)
);

CREATE TABLE employee_profiles (
    profile_id INT AUTO_INCREMENT PRIMARY KEY,
    phone VARCHAR(20),
    pan_number VARCHAR(20),
    employee_id INT UNIQUE,
    CONSTRAINT fk_profile_employee
        FOREIGN KEY (employee_id)
        REFERENCES employees(employee_id)
);

INSERT INTO employees (employee_name, email, salary) VALUES
('Ravi Kumar', 'ravi@example.com', 75000.00),
('Anita Sharma', 'anita@example.com', 65000.00),
('Manoj Verma', 'manoj@example.com', 85000.00);

INSERT INTO employee_profiles (phone, pan_number, employee_id) VALUES
('9876543210', 'ABCDE1234F', 1),
('9123456780', 'XYZAB5678K', 2),
('9988776655', 'LMNOP9999Q', 3);

SELECT * FROM employees;
SELECT * FROM employee_profiles;