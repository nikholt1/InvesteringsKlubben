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

    public UserRepository() {
        this.users = new ArrayList<>();
        readFile();
    }

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
                lineScanner.next();
                String createdAt = lineScanner.next();
                String lastUpdated = lineScanner.next();

                users.add(new User(userID, fullName, email, birthDate, createdAt, lastUpdated));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public void writeNewUserToFile(String name, String email, String birthDate) {
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
                    formattedBirthDate + ";" + 100_000 + ";" +
                    formattedCreated + ";" + formattedUpdated + "\n";
            writer.write(formattedLine);
            writer.close();
            readFile();

        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        return users;
    }
}
