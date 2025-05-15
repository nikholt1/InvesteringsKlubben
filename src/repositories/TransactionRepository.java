package repositories;

import models.Transaction;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionRepository {

    private List<Transaction> transactions;
    private final String PATH = "src/repositories/transactions.csv";


    public TransactionRepository() {
        this.transactions = new ArrayList<>();
        readList();
    }

    public void readList() {
        transactions.clear();
        try {
            Scanner reader = new Scanner(new File(PATH));
            // Skips the header describing the format
            if (reader.hasNextLine()) {
                reader.nextLine();
            }

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(";");

                int id = Integer.parseInt(lineScanner.next());
                int userId = Integer.parseInt(lineScanner.next());

                String localDate = lineScanner.next();
                String ticker = lineScanner.next();

                double price = Double.parseDouble(lineScanner.next().replace(',', '.'));
                String currency = lineScanner.next();
                String order_type = lineScanner.next();
                int quantity = Integer.parseInt(lineScanner.next());

                transactions.add(new Transaction(id, userId, localDate, ticker, price, currency, order_type, quantity));

            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Could not find file");
        }
    }

    public void writeNewTransactionToFile(int userId, String ticker, double price, String order_type, int quantity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            Writer writer = new FileWriter((PATH), true);

            int ID = 0;
            for (Transaction transaction : transactions) {
                ID++;
            }
            int newID = ID + 1;
            LocalDate createdDate = LocalDate.now();
            String formattedCreatedDate = createdDate.format(formatter);

            String currency = "DKK";

            String formattedLine = "\n"
                    + newID + ";"
                    + userId + ";"
                    + formattedCreatedDate + ";"
                    + ticker + ";"
                    + price + ";"
                    + currency + ";"
                    + order_type + ";"
                    + quantity;

            writer.write(formattedLine);
            writer.close();
            readList();
        } catch (IOException e) {
            System.out.println("File not found or could not be written to");
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
