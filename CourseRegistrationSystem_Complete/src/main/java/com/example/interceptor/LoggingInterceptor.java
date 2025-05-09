package com.example.interceptor;

import com.example.model.Student;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.*;
import java.util.Date;

public class LoggingInterceptor extends HandlerInterceptorAdapter {

    // Logs user actions like login and registration
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("loggedInStudent");
        String userInfo = (student != null) ? "Student ID: " + student.getStudentId() : "No user";
        System.out.println("Action: " + method + " " + uri + " by " + userInfo + " at " + new Date());
        return true;
    }
}