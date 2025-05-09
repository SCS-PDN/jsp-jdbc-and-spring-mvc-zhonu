-- Setup for university database

CREATE DATABASE IF NOT EXISTS university_db;
USE university_db;

-- Create students table
CREATE TABLE IF NOT EXISTS students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100)
);

-- Create courses table
CREATE TABLE courses (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    instructor VARCHAR(100),
    credits INT
);

-- Create registrations table
CREATE TABLE registrations (
    reg_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
	date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id)
);

-- Insert sample data for testing
INSERT INTO students (name, email, password) VALUES
('Thilini Chathurika', 's20201@sci.pdn.ac.lk', 's20201'),
('Thedini Parindya', 's20809@sci.pdn.ac.lk', 's20809');

INSERT INTO courses (name, instructor, credits) VALUES
('Data Structures', 'Dr. Adams', 3),
('Algorithms', 'Prof. Brown', 2),
('Web Programming', 'Ms. Clark', 3);

-- Quick check to see if data loaded (I always do this to be sure)
SELECT * FROM students;