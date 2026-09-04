package com.dental.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Singleton Instance
    private static DBConnection instance;
    private Connection connection;

    // Database Configurations
    private static final String URL = "jdbc:mysql://localhost:3306/dental_db";
    private static final String USER = "root";
    private static final String PASSWORD = "" ;
    // Private Constructor (Singleton Pattern)
    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Public method to get Singleton instance
    public static synchronized DBConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}