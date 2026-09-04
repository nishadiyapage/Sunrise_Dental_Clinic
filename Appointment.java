package com.dental.dao;

import java.sql.Date;
import java.sql.Time;

/**
 * Model class representing an Appointment entity in the system.
 */
public class Appointment {
    private int id;
    private String patientUsername;
    private String patientName;
    private String address;
    private String contactNumber;
    private String doctorName;
    private String treatmentType;
    private Date appointmentDate;
    private Time appointmentTime;
    private String status;
    private double totalCost;

    // Default Constructor
    public Appointment() {}

    // Parameterized Constructor including all details
    public Appointment(int id, String patientUsername, String patientName, String address, 
                       String contactNumber, String doctorName, String treatmentType, 
                       Date appointmentDate, Time appointmentTime, String status, double totalCost) {
        this.id = id;
        this.patientUsername = patientUsername;
        this.patientName = patientName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.doctorName = doctorName;
        this.treatmentType = treatmentType;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPatientUsername() { return patientUsername; }
    public void setPatientUsername(String patientUsername) { this.patientUsername = patientUsername; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getTreatmentType() { return treatmentType; }
    public void setTreatmentType(String treatmentType) { this.treatmentType = treatmentType; }

    public Date getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(Date appointmentDate) { this.appointmentDate = appointmentDate; }

    public Time getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(Time appointmentTime) { this.appointmentTime = appointmentTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
}