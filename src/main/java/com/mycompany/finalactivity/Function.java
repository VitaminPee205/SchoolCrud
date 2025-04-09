package com.mycompany.finalactivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class Function{
    
    private String fullName;
    private String birthDate;
    private String gender;
    private String contactNumber;
    private String year;
    private String studentID;

    // Getters and Setters
    public String getFullName() {
        return fullName;
        
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    // Method to validate and generate Student ID if required
    public void validateAndGenerateStudentID() throws Exception {
        if (year.equals("1st Year")) {
            if (!studentID.isEmpty()) {
                throw new Exception("Student ID must be empty for 1st Year students.");
            }
            Random random = new Random();
            this.studentID = String.valueOf(10000 + random.nextInt(90000));
        } else {
            if (studentID.isEmpty()) {
                throw new Exception("Please enter the Student ID for non-1st Year students.");
            }
        }
    }

    // Method to save the data to the database
    public void saveToDatabase(String databaseURL) throws Exception {
        try (Connection connection = DriverManager.getConnection(databaseURL)) {
            String sql = "INSERT INTO Enrollment (StudentID, FullName, BirthDate, Gender, ContactNumber, Year) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, studentID);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, birthDate);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, contactNumber);
            preparedStatement.setString(6, year);

            preparedStatement.executeUpdate();
        }
    }
}


