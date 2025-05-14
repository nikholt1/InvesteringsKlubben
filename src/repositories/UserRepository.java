package repositories;

import models.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepository {

    private  List<User> users;
    private final String PATH = "src/repositories/users.csv";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    //TODO

    public UserRepository() {
        this.users = new ArrayList<>();
        readFile();
    }

    //List<User>

    // readFile()
    public void readFile() {
        users.clear();
        try {
            Scanner reader = new Scanner(new File(PATH));
            if (reader.hasNextLine()) {
                reader.nextLine();
            }
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(";");
                int userID = Integer.parseInt(lineScanner.next());
                String fullName = lineScanner.next();
                String email = lineScanner.next();
                String birthDate = lineScanner.next();
                double initialCash = Double.parseDouble(lineScanner.next());
                String createdAt = lineScanner.next();
                String lastUpdated = lineScanner.next();
                users.add(new User(userID, fullName, email, birthDate, initialCash, createdAt, lastUpdated));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    // writeToFile()
    public void writeNewUserToFile(String name, String email, double balance, String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            Writer writer = new FileWriter(PATH, true); // append mode

            int ID = 0;
            for (User user : users) {
                ID++;
            }
            int newID = ID + 1;

            LocalDate created = LocalDate.now();
            LocalDate updated = LocalDate.now();
            LocalDate parsedBirthDate = LocalDate.parse(birthDate, formatter);

            String formattedBirthDate = parsedBirthDate.format(formatter);
            String formattedCreated = created.format(formatter);
            String formattedUpdated = updated.format(formatter);

            String formattedLine = newID + ";" + name + ";" + email + ";" +
                    formattedBirthDate + ";" + balance + ";" +
                    formattedCreated + ";" + formattedUpdated + "\n";
            writer.write(formattedLine);
            writer.close();
            readFile();

        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    //depositByUserID()
    public boolean depositToAccout(int userID, double value) {
        readFile();
        System.out.println(userID);
        boolean userFound = false;
        for (User user : users) {
            if (user.getUserID() == userID) {
                System.out.println("found " + user);
                double userCurrentValue = user.getBalance();
                user.setBalance(userCurrentValue + value);
                System.out.println("updated user cash with" + user.getBalance());
                user.setUpdateded(LocalDate.now());
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            System.out.println("User not found");
            return false;
        }
        try {
            Writer writer = new FileWriter(PATH);
            writer.write("user_id;full_name;email;birth_date;initial_cash_DKK;created_at;last_updated");
            for (User user : users) {
//                System.out.println(user);
                String formatted = user.getUserID() + ";" +
                        user.getFullName() + ";" +
                        user.getEmail() + ";" +
                        user.getBirthDate().format(formatter) + ";" +
                        user.getBalance() + ";" +
                        user.getCreatedAt().format(formatter) + ";" +
                        user.getUpdateded().format(formatter) + "\n";
                writer.write(formatted);
            }
            writer.close();
            readFile();
            return true;
        } catch (IOException e) {
            System.out.println("Error in UserRepository.depositByUserID");
            e.printStackTrace();
            return false;
        }

    }

    //checkAccountCashBalance();
    public boolean checkAccountCashBalance(int userID, double value) {
        for(User user : users) {
            if (user.getUserID() == userID) {
                if (user.getBalance() < value) {
                    System.out.println("Error in UserRepository.withdrawByUserID: not enough money");
                    return false;
                } else {
                    System.out.println(user.getBalance() + " Acceptable value");
                    return true;
                }
            }
        }
        return false;
    }

    //withdrawByUserID()
    public boolean withdrawFromAccount(int userID, double value) {
        for (User user : users) {

            if (user.getUserID() == userID) {
                System.out.println("found " + user + "in repository" );
                if (!checkAccountCashBalance(userID, value)) {
                    System.out.println("Error in UserRepository.withdrawByUserID: not enough money");
                    return false;
                } else {
                    double userCurrentValue = user.getBalance();
                    user.setBalance(userCurrentValue - value);
                    System.out.println("Account balance updated to: " + user.getBalance());
                    user.setUpdateded(LocalDate.now());
                }

            }
        }
        try {
            Writer writer = new FileWriter(PATH);
            writer.write("user_id;full_name;email;birth_date;initial_cash_DKK;created_at;last_updated");
            for (User user : users) {
//                System.out.println(user);
                String formatted = user.getUserID() + ";" +
                        user.getFullName() + ";" +
                        user.getEmail() + ";" +
                        user.getBirthDate().format(formatter) + ";" +
                        user.getBalance() + ";" +
                        user.getCreatedAt().format(formatter) + ";" +
                        user.getUpdateded().format(formatter) + "\n";
                writer.write(formatted);
            }
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error in UserRepository.depositByUserID");
            e.printStackTrace();
            return false;
        }
    }





    public void updateUserCashData(int userID, int value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (User user : users) {
            if (user.getUserID() == userID) {
                double userCash = user.getBalance();
                user.setBalance(userCash += value);
                user.setUpdateded(LocalDate.now());
            }
        }
        try {
            FileWriter writer = new FileWriter(PATH);

            for (User user : users) {
                String formatted = user.getUserID() + ";" +
                        user.getFullName() + ";" +
                        user.getEmail() + ";" +
                        user.getBirthDate().format(formatter) + ";" +
                        user.getBalance() + ";" +
                        user.getCreatedAt().format(formatter) + ";" +
                        user.getUpdateded().format(formatter) + "\n";
                writer.write(formatted);
            }

            writer.close();
            readFile();
        } catch (IOException e) {
            System.out.println("File not found or could not be written to");
            e.printStackTrace();
        }
    }

    //getUser()
//    public User findUserMyEmail(String email) {
//        User result = null;
//        for (User user : users) {
//            if(user.getEmail().equals(email)) {
//                result = user;
//
//            }
//        }
//        if (result == null) {
//            System.out.println("Systemet kunne ikke finde den indtastede bruger.");
//
//        }
//        return result;
//    }



    public List<User> getUsers() {
        return users;
    }


}
