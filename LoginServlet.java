package com.dental.controller;

import com.dental.dao.User;
import com.dental.dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.validateUser(username, password);

        if (user != null) {
            // Login Successful
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("admin_dashboard.html");
            } else {
                response.sendRedirect("patient_dashboard.html");
            }
        } else {
            // Login Failed: Check if user exists
            boolean userExists = userDAO.checkUserExists(username);

            if (!userExists) {
                // Not registered user
                response.sendRedirect("login.html?error=not_registered");
            } else {
                // Registered user, but wrong password
                response.sendRedirect("login.html?error=invalid_credentials");
            }
        }
    }
}