package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;

@Repository
public class RegistrationDAO {

    @Autowired
    private DataSource dataSource;

    // Registers a student for a course if not already registered
    public boolean registerCourse(int studentId, int courseId) {
        String checkSql = "SELECT * FROM registrations WHERE student_id = ? AND course_id = ?";
        String insertSql = "INSERT INTO registrations (student_id, course_id, date) VALUES (?, ?, ?)";
        try (Connection connDb = dataSource.getConnection();
             PreparedStatement checkStmt = connDb.prepareStatement(checkSql)) {
            checkStmt.setInt(1, studentId);
            checkStmt.setInt(2, courseId);
            ResultSet result = checkStmt.executeQuery();
            if (result.next()) {
                System.out.println("Duplicate registration attempt: Student " + studentId + ", Course " + courseId);
                return false;
            }

            try (PreparedStatement insertStmt = connDb.prepareStatement(insertSql)) {
                insertStmt.setInt(1, studentId);
                insertStmt.setInt(2, courseId);
                insertStmt.setDate(3, Date.valueOf(LocalDate.now()));
                insertStmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("DB error: " + ex.getMessage());
            return false;
        }
    }

    // Checks if a student is already registered for a course
    public boolean isStudentRegistered(int studentId, int courseId) {
        String checkSql = "SELECT * FROM registrations WHERE student_id = ? AND course_id = ?";
        try (Connection connDb = dataSource.getConnection();
             PreparedStatement stmt = connDb.prepareStatement(checkSql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            ResultSet result = stmt.executeQuery();
            boolean isRegistered = result.next();
            System.out.println("Checked registration for Student " + studentId + ", Course " + courseId + ": " + isRegistered);
            return isRegistered;
        } catch (SQLException ex) {
            System.err.println("DB error in isStudentRegistered: " + ex.getMessage());
            return false;
        }
    }
}