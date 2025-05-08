package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {


    private int userID;
    private String fullName;
    private String email;
    private LocalDate birthDate;
    private double balance;
    private LocalDate createdAt;
    private LocalDate updateded;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    //TODO


    public User(int userID, String fullName, String email, String birthDate, double initCash, String createdAt, String updateded) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.birthDate = LocalDate.parse(birthDate, formatter);
        this.balance = initCash;
        this.createdAt = LocalDate.parse(createdAt, formatter);
        this.updateded = LocalDate.parse(updateded, formatter);
    }


    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", initCash=" + balance +
                ", createdAt=" + createdAt +
                ", updateded=" + updateded +
                '}';
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdateded() {
        return updateded;
    }

    public void setUpdateded(LocalDate updateded) {
        this.updateded = updateded;
    }


    /// Attributes

    /// Constructor


    /// Getters


    /// Setters


    /// toString
}
