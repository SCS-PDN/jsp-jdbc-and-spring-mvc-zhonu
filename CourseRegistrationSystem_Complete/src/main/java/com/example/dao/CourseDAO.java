package com.example.dao;

import com.example.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class CourseDAO {

    @Autowired
    private DataSource dataSource;

    // Gets all courses from the database
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sqlQuery = "SELECT * FROM courses";
        try (Connection dbConn = dataSource.getConnection();
             Statement stmt = dbConn.createStatement();
             ResultSet results = stmt.executeQuery(sqlQuery)) {
            while (results.next()) {
                Course course = new Course();
                course.setCourseId(results.getInt("course_id"));
                course.setName(results.getString("name"));
                course.setInstructor(results.getString("instructor"));
                course.setCredits(results.getInt("credits"));
                courses.add(course);
            }
            System.out.println("Fetched " + courses.size() + " courses from DB");
        } catch (SQLException ex) {
            System.err.println("Database error: " + ex.getMessage());
        }
        return courses;
    }
}