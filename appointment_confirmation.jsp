<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dental.dao.Appointment" %>
<%
    Appointment app = (Appointment) request.getAttribute("appointment");
    if (app == null) {
        app = (Appointment) session.getAttribute("appointment");
    }

    // Fixed doctor channelling fee
    double channellingFee = 1500.00;
    
    // Retrieve total bill directly from Database object
    double totalBill = (app != null) ? app.getTotalCost() : 0.00;
    
    // Calculate actual treatment cost (Total Bill - Channelling Fee)
    double treatmentCost = (totalBill >= channellingFee) ? (totalBill - channellingFee) : 0.00;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointment Details & Bill - Sunrise Dental Clinic</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: Arial, sans-serif; }
        body { background: #f4f6f9; min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 20px; }
        .receipt-card { width: 100%; max-width: 600px; background: #ffffff; padding: 30px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
        .receipt-header { text-align: center; margin-bottom: 20px; }
        .receipt-header h2 { color: #0284c7; font-size: 24px; margin-bottom: 5px; }
        .receipt-header p { color: #64748b; font-size: 14px; }
        .divider { border-top: 1px dashed #cbd5e1; margin: 15px 0; }
        .info-row { display: flex; justify-content: space-between; margin-bottom: 12px; font-size: 15px; }
        .info-label { font-weight: bold; color: #334155; }
        .info-value { color: #0f172a; text-align: right; }
        .fee-breakdown { background: #f8fafc; padding: 12px; border-radius: 6px; margin-top: 10px; border: 1px solid #e2e8f0; }
        .total-row { display: flex; justify-content: space-between; margin-top: 15px; padding-top: 15px; border-top: 2px solid #0f172a; font-size: 18px; font-weight: bold; color: #0f172a; }
        .btn-group { display: flex; gap: 12px; margin-top: 25px; }
        .btn-print { flex: 1; background-color: #28a745; color: white; padding: 12px; border: none; border-radius: 6px; font-weight: bold; cursor: pointer; text-align: center; }
        .btn-exit { flex: 1; background-color: #dc3545; color: white; padding: 12px; border: none; border-radius: 6px; font-weight: bold; cursor: pointer; text-align: center; text-decoration: none; }
        @media print { .btn-group { display: none; } }
    </style>
</head>
<body>

    <div class="receipt-card">
        <div class="receipt-header">
            <h2>SUNRISE DENTAL CLINIC</h2>
            <p>Official Payment Receipt & Appointment Slip</p>
        </div>

        <div class="divider"></div>

        <div class="info-row">
            <span class="info-label">Appointment Number:</span>
            <span class="info-value">#<%= (app != null) ? app.getId() : "N/A" %></span>
        </div>
        <div class="info-row">
            <span class="info-label">Patient Name:</span>
            <span class="info-value"><%= (app != null) ? app.getPatientName() : "N/A" %></span>
        </div>
        <div class="info-row">
            <span class="info-label">Address:</span>
            <span class="info-value"><%= (app != null) ? app.getAddress() : "N/A" %></span>
        </div>
        <div class="info-row">
            <span class="info-label">Contact Number:</span>
            <span class="info-value"><%= (app != null) ? app.getContactNumber() : "N/A" %></span>
        </div>
        <div class="info-row">
            <span class="info-label">Assigned Dentist:</span>
            <span class="info-value"><%= (app != null) ? app.getDoctorName() : "N/A" %></span>
        </div>
        <div class="info-row">
            <span class="info-label">Treatment Type:</span>
            <span class="info-value"><%= (app != null) ? app.getTreatmentType() : "N/A" %></span>
        </div>
        <div class="info-row">
            <span class="info-label">Date & Time:</span>
            <span class="info-value"><%= (app != null) ? app.getAppointmentDate() + " " + app.getAppointmentTime() : "N/A" %></span>
        </div>

        <!-- Bill Breakdown Section -->
        <div class="fee-breakdown">
            <div class="info-row">
                <span class="info-label">Treatment Fee:</span>
                <span class="info-value">Rs. <%= String.format("%.2f", treatmentCost) %></span>
            </div>
            <div class="info-row" style="margin-bottom: 0;">
                <span class="info-label">Doctor Channelling Fee:</span>
                <span class="info-value">Rs. <%= String.format("%.2f", channellingFee) %></span>
            </div>
        </div>

        <div class="total-row">
            <span>Total Bill Amount:</span>
            <span>Rs. <%= String.format("%.2f", totalBill) %></span>
        </div>

        <div class="btn-group">
            <button onclick="window.print()" class="btn-print">Print / Download Bill</button>
            <a href="LogoutServlet" class="btn-exit">Exit System</a>
        </div>
    </div>

</body>
</html>