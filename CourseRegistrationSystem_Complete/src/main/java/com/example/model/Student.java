package com.example.model;

// Represents a student in the university system
public class Student {
    private int studentId;
    private String name;
    private String email;
    private String password;

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int id) {
        this.studentId = id;
    }

    public String getName() { return name; }
    public void setName(String studentName) {
        this.name = studentName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String emailAddr) { this.email = emailAddr; }

    public String getPassword() { return password; }
    public void setPassword(String pass) { this.password = pass; }

    // For debugging
    @Override
    public String toString() {
        return "Student: " + name + " (ID: " + studentId + ", Email: " + email + ")";
    }
}