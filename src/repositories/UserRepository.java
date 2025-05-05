package repositories;

import models.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepository {

    private List<User> users;
    private final String PATH = "src/repositories/users.csv";
    //TODO

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    //List<User>

    // readFile()
    public void readFile() {
        try {
            Scanner reader = new Scanner(new File(PATH));
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(";");
                int userID = Integer.parseInt(lineScanner.next());
                String fullName = lineScanner.next();
                String email = lineScanner.next();
                LocalDate birthDate = LocalDate.parse(lineScanner.next());
                double initialCash = Double.parseDouble(lineScanner.next());
                LocalDate createdAt = LocalDate.parse(lineScanner.next());
                LocalDate lastUpdated = LocalDate.parse(lineScanner.next());
                users.add(new User(userID, fullName, email, birthDate, initialCash, createdAt, lastUpdated));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    // writeToFile()
    public void writeToFile(String name, String email, LocalDate birthDate) {
        try {
            Writer writer = new FileWriter(new File(PATH), true);
            readFile();
            int newID = 0;
            for (User user : users) {
                newID++;
            }
            LocalDate updated = LocalDate.now();
            LocalDate created = LocalDate.now();
            String formatted = name + ";" + email + ";" + "100_000" + ";" + created + ";" + updated;
            writer.write(formatted);
            writer.close();

        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    //getUser()
    public User findUserMyEmail(String email) {
        User result = null;
        for (User user : users) {
            if(user.getEmail().equals(email)) {
                result = user;

            }
        }
        if (result == null) {
            System.out.println("Systemet kunne ikke finde den indtastede bruger.");

        }
        return result;
    }



    public List<User> getUsers() {
        return users;
    }


}
