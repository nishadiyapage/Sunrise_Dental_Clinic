package com.dental.controller;

import com.dental.dao.User;
import com.dental.dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        UserDAO userDAO = new UserDAO();
        boolean isRegistered = userDAO.registerUser(user);

        if (isRegistered) {
            response.sendRedirect("login.html?registration=success");
        } else {
            response.sendRedirect("register.html?error=failed");
        }
    }
}