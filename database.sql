USE dental_db;

-- Drop old tables completely
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS users;

-- Re-create Users Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Re-create Appointments Table with updated constraints & data types
CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_username VARCHAR(50) NOT NULL,
    patient_name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    doctor_name VARCHAR(100) NOT NULL,
    treatment_type VARCHAR(100) NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    status VARCHAR(20) DEFAULT 'CONFIRMED',
    total_cost DECIMAL(10,2) DEFAULT 0.00,
    CONSTRAINT fk_patient_user FOREIGN KEY (patient_username) REFERENCES users(username) ON DELETE CASCADE
);

-- Insert Default Users
INSERT INTO users (username, password, role) VALUES 
('admin', 'admin123', 'ADMIN'),
('patient1', 'patient123', 'PATIENT');