package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {


    private int userID;
    private String fullName;
    private String email;
    private LocalDate birthDate;
    private final double INIT_CASH;
    private LocalDate createdAt;
    private LocalDate updateded;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    //TODO


    public User(int userID, String fullName, String email, String birthDate, String createdAt, String updateded) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.birthDate = LocalDate.parse(birthDate, formatter);
        this.INIT_CASH = 100_000;
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
                ", initCash=" + INIT_CASH +
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

    public double getINIT_CASH() {
        return INIT_CASH;
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
}
