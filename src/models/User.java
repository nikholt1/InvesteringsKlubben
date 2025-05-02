package models;

import java.time.LocalDate;

public class User {


    private int userID;
    private String fullName;
    private String email;
    private LocalDate birthDate;
    private double initCash;
    private LocalDate createdAt;
    private LocalDate updateded;
    //TODO


    public User(int userID, String fullName, String email, LocalDate birthDate, double initCash, LocalDate createdAt, LocalDate updateded) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
        this.initCash = initCash;
        this.createdAt = createdAt;
        this.updateded = updateded;
    }


    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", initCash=" + initCash +
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

    public double getInitCash() {
        return initCash;
    }

    public void setInitCash(double initCash) {
        this.initCash = initCash;
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
