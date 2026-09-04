package com.dental.dao;

import com.dental.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object class for handling database operations related to Appointments.
 */
public class AppointmentDAO {

    /**
     * Inserts a new appointment record and returns the generated appointment ID.
     * 
     * @param appointment The appointment object containing booking details.
     * @return The auto-generated appointment ID if successful, or -1 on failure.
     */
    public int createAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (patient_username, patient_name, address, contact_number, doctor_name, treatment_type, appointment_date, appointment_time, status, total_cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int generatedId = -1;

        try {
            conn = DBConnection.getInstance().getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, appointment.getPatientUsername());
            stmt.setString(2, appointment.getPatientName());
            stmt.setString(3, appointment.getAddress());
            stmt.setString(4, appointment.getContactNumber());
            stmt.setString(5, appointment.getDoctorName());
            stmt.setString(6, appointment.getTreatmentType());
            stmt.setDate(7, appointment.getAppointmentDate());
            stmt.setTime(8, appointment.getAppointmentTime());
            stmt.setString(9, appointment.getStatus());
            stmt.setDouble(10, appointment.getTotalCost());

            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
        
        return generatedId;
    }

    /**
     * Retrieves an appointment by its unique appointment ID (number).
     * 
     * @param appointmentId The ID of the appointment to search for.
     * @return Appointment object if found, otherwise null.
     */
    public Appointment getAppointmentById(int appointmentId) {
        String sql = "SELECT * FROM appointments WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Appointment appointment = null;

        try {
            conn = DBConnection.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, appointmentId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                appointment = new Appointment(
                    rs.getInt("id"),
                    rs.getString("patient_username"),
                    rs.getString("patient_name"),
                    rs.getString("address"),
                    rs.getString("contact_number"),
                    rs.getString("doctor_name"),
                    rs.getString("treatment_type"),
                    rs.getDate("appointment_date"),
                    rs.getTime("appointment_time"),
                    rs.getString("status"),
                    rs.getDouble("total_cost")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }

        return appointment;
    }

    /**
     * Retrieves all appointments ordered by appointment date.
     * 
     * @return List of all appointments.
     */
    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments ORDER BY appointment_date ASC";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Appointment app = new Appointment(
                    rs.getInt("id"),
                    rs.getString("patient_username"),
                    rs.getString("patient_name"),
                    rs.getString("address"),
                    rs.getString("contact_number"),
                    rs.getString("doctor_name"),
                    rs.getString("treatment_type"),
                    rs.getDate("appointment_date"),
                    rs.getTime("appointment_time"),
                    rs.getString("status"),
                    rs.getDouble("total_cost")
                );
                list.add(app);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        }
        
        return list;
    }
}