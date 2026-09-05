package com.dental.controller;

import com.dental.dao.Appointment;
import com.dental.dao.AppointmentDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewReceiptServlet")
public class ViewReceiptServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int appId = Integer.parseInt(request.getParameter("appointmentId"));
            AppointmentDAO dao = new AppointmentDAO();
            Appointment appointment = dao.getAppointmentById(appId); // Ensure this method exists in your DAO

            request.setAttribute("appointment", appointment);
            RequestDispatcher dispatcher = request.getRequestDispatcher("appointment_confirmation.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("patient_dashboard.html");
        }
    }
}