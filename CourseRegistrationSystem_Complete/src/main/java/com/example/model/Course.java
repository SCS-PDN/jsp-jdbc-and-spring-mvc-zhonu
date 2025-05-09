package com.example.model;

// Represents a course in the university system
public class Course {
    private int courseId;
    private String name;
    private String instructor;
    private int credits;

    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int id) {
        this.courseId = id;
    }

    public String getName() { return name; }
    public void setName(String courseName) {
        this.name = courseName;
    }

    public String getInstructor() {
        return instructor;
    }
    public void setInstructor(String prof) { this.instructor = prof; }

    public int getCredits() { return credits; }
    public void setCredits(int creditCount) { this.credits = creditCount; }

    // For debugging
    @Override
    public String toString() {
        return "Course: " + name + " (ID: " + courseId + ")";
    }
}