package com.example.controller;

import com.example.dao.StudentDAO;
import com.example.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/login")
    public String displayLoginPage() {
        return "login";
    }

    // Handles login form submission
    @PostMapping("/login")
    public String processLogin(
            @RequestParam("email") String userEmail,
            @RequestParam("password") String userPassword,
            HttpSession session,
            Model model) {

        System.out.println("Login attempt: " + userEmail);

        Student loggedInStudent = studentDAO.checkLogin(userEmail, userPassword);

        if (loggedInStudent != null) {
            session.setAttribute("loggedInStudent", loggedInStudent);
            return "redirect:/courses";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password.");
            return "login";
        }
    }
}