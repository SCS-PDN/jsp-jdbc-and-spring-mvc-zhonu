package com.example.dao;

import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;

@Repository
public class StudentDAO {

    @Autowired
    private DataSource dataSource;

    // Checks student login credentials
    public Student checkLogin(String email, String password) {
        String sqlQuery = "SELECT * FROM students WHERE email = ? AND password = ?";
        try (Connection dbConn = dataSource.getConnection();
             PreparedStatement stmt = dbConn.prepareStatement(sqlQuery)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet studentData = stmt.executeQuery();
            if (studentData.next()) {
                Student student = new Student();
                student.setStudentId(studentData.getInt("student_id"));
                student.setName(studentData.getString("name"));
                student.setEmail(studentData.getString("email"));
                student.setPassword(studentData.getString("password"));
                System.out.println("Student found: " + email);
                return student;
            }
        } catch (SQLException ex) {
            System.err.println("DB error: " + ex.getMessage());
        }
        return null;
    }
}