package com.dental.controller;

import com.dental.dao.Appointment;
import com.dental.dao.AppointmentDAO;
import com.dental.dao.User;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {

    // Define fixed doctor channelling fee constant
    private static final double CHANNELLING_FEE = 1500.0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("currentUser") : null;

        if (user == null) {
            response.sendRedirect("login.html");
            return;
        }

        // Retrieve form input parameters
        String patientName = request.getParameter("patientName");
        String address = request.getParameter("address");
        String contactNumber = request.getParameter("contactNumber");
        String doctorName = request.getParameter("doctorName");
        String treatmentType = request.getParameter("treatmentType");
        String dateStr = request.getParameter("appointmentDate");
        String timeStr = request.getParameter("appointmentTime");

        try {
            Date appDate = Date.valueOf(dateStr);
            Time appTime = Time.valueOf(timeStr + ":00");

            // Calculate treatment fee and total cost including channelling fee
            double treatmentCost = calculateTreatmentCost(treatmentType);
            double totalCost = treatmentCost + CHANNELLING_FEE;

            // Populate Appointment Object
            Appointment appointment = new Appointment();
            appointment.setPatientUsername(user.getUsername());
            appointment.setPatientName(patientName);
            appointment.setAddress(address);
            appointment.setContactNumber(contactNumber);
            appointment.setDoctorName(doctorName);
            appointment.setTreatmentType(treatmentType);
            appointment.setAppointmentDate(appDate);
            appointment.setAppointmentTime(appTime);
            appointment.setStatus("CONFIRMED");
            
            // Set total cost (Treatment Fee + Channelling Fee)
            appointment.setTotalCost(totalCost);

            // Save to database
            AppointmentDAO dao = new AppointmentDAO();
            int generatedAppointmentId = dao.createAppointment(appointment);

            if (generatedAppointmentId > 0) {
                // Set generated ID into object
                appointment.setId(generatedAppointmentId);
                
                // Pass appointment object to JSP
                request.setAttribute("appointment", appointment);
                
                // Forward request to intermediate confirmation success card
                RequestDispatcher dispatcher = request.getRequestDispatcher("appointment_success.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("patient_dashboard.html?status=error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("patient_dashboard.html?status=error");
        }
    }

    /**
     * Helper method to calculate base treatment cost.
     */
    private double calculateTreatmentCost(String treatmentType) {
        if (treatmentType == null) return 0.0;
        
        switch (treatmentType) {
            case "Cleaning":
                return 2000.0;
            case "Filling":
                return 3500.0;
            case "Extraction":
                return 4500.0;
            case "Root Canal":
                return 8500.0;
            case "Braces":
                return 50000.0; // Added for Dental Braces Treatment
            default:
                return 0.0;
        }
    }
}