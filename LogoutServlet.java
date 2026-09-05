package com.dental.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller Servlet to handle user logout and session destruction.
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Step 1: Get the current active session without creating a new one
        HttpSession session = request.getSession(false);
        
        // Step 2: If a session exists, clear and invalidate it
        if (session != null) {
            session.invalidate(); // Destroys user session data
        }
        
        // Step 3: Redirect the user back to the login page
        response.sendRedirect("login.html");
    }
}