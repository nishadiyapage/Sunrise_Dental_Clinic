<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dental.dao.Appointment" %>
<%
    Appointment app = (Appointment) request.getAttribute("appointment");
    int appId = (app != null) ? app.getId() : 0;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Confirmed - Sunrise Dental Clinic</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: Arial, sans-serif; }
        body { 
            background: linear-gradient(rgba(15, 23, 42, 0.7), rgba(15, 23, 42, 0.7)), 
                        url('images/bg1.jpg') no-repeat center center fixed;
            background-size: cover;
            min-height: 100vh; 
            display: flex; 
            align-items: center; 
            justify-content: center; 
            padding: 20px; 
        }
        .card { 
            width: 100%; 
            max-width: 420px; 
            background: #ffffff; 
            padding: 35px 25px; 
            border-radius: 12px; 
            box-shadow: 0 10px 25px rgba(0,0,0,0.3); 
            text-align: center; 
        }
        .icon-circle {
            width: 50px;
            height: 50px;
            background-color: #d1fae5;
            color: #10b981;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 24px;
            margin: 0 auto 15px auto;
            font-weight: bold;
        }
        .card h2 { color: #0f172a; font-size: 22px; margin-bottom: 8px; }
        .card p { color: #64748b; font-size: 13px; line-height: 1.5; margin-bottom: 20px; }
        
        .app-box {
            border: 2px dashed #0284c7;
            background-color: #f0f9ff;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 25px;
        }
        .app-box span { display: block; font-size: 11px; font-weight: bold; color: #0284c7; letter-spacing: 0.5px; }
        .app-box h3 { font-size: 28px; color: #0284c7; margin-top: 5px; }

        .btn-group { display: flex; gap: 10px; }
        .btn-another {
            flex: 1;
            background-color: #cbd5e1;
            color: #334155;
            padding: 12px;
            border-radius: 6px;
            text-decoration: none;
            font-weight: bold;
            font-size: 14px;
            transition: 0.2s;
        }
        .btn-another:hover { background-color: #94a3b8; color: white; }
        
        .btn-receipt {
            flex: 1;
            background-color: #0284c7;
            color: white;
            padding: 12px;
            border-radius: 6px;
            text-decoration: none;
            font-weight: bold;
            font-size: 14px;
            transition: 0.2s;
        }
        .btn-receipt:hover { background-color: #0369a1; }
    </style>
</head>
<body>

    <div class="card">
        <div class="icon-circle">&#10004;</div>
        <h2>Booking Confirmed!</h2>
        <p>Your appointment has been successfully scheduled with Sunrise Dental Clinic.</p>

        <div class="app-box">
            <span>YOUR APPOINTMENT NUMBER</span>
            <h3>#<%= appId %></h3>
        </div>

        <div class="btn-group">
            <a href="patient_dashboard.html" class="btn-another">Book Another</a>
            <a href="ViewReceiptServlet?appointmentId=<%= appId %>" class="btn-receipt">View Receipt</a>
        </div>
    </div>

</body>
</html>