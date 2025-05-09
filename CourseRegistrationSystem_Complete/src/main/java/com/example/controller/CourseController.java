package com.example.controller;

import com.example.dao.CourseDAO;
import com.example.dao.RegistrationDAO;
import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;

@Controller
public class CourseController {

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private RegistrationDAO regDAO;

    // Fetches all courses for display
    @GetMapping("/courses")
    public String showCourses(Model model) {
        model.addAttribute("courseList", courseDAO.getAllCourses());
        return "courses";
    }

    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable("courseId") int courseId, HttpSession session, Model model) {
        Student currentStudent = (Student) session.getAttribute("loggedInStudent");

        System.out.println("Registration attempt for course ID: " + courseId);

        if (currentStudent == null) {
            return "redirect:/login";
        }

        // Check if student is already registered for the course
        boolean alreadyRegistered = regDAO.isStudentRegistered(currentStudent.getStudentId(), courseId);
        if (alreadyRegistered) {
            model.addAttribute("errorMessage", "You are already registered for this course!");
            return "courses";
        }

        regDAO.registerCourse(currentStudent.getStudentId(), courseId);
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }
}