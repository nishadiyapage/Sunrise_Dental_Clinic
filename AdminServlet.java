package com.dental.controller;

import com.dental.dao.Appointment;
import com.dental.dao.AppointmentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        AppointmentDAO dao = new AppointmentDAO();
        List<Appointment> list = dao.getAllAppointments();

        // Convert list to JSON string manually (without external libraries)
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            Appointment app = list.get(i);
            json.append("{")
                .append("\"id\":").append(app.getId()).append(",")
                .append("\"patientUsername\":\"").append(app.getPatientUsername()).append("\",")
                .append("\"doctorName\":\"").append(app.getDoctorName()).append("\",")
                .append("\"appointmentDate\":\"").append(app.getAppointmentDate()).append("\",")
                .append("\"appointmentTime\":\"").append(app.getAppointmentTime()).append("\",")
                .append("\"status\":\"").append(app.getStatus()).append("\"")
                .append("}");
            if (i < list.size() - 1) json.append(",");
        }
        json.append("]");

        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();
    }
}