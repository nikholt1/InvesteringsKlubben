package repositories;

import models.Currency;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencyRepository {

    private List<Currency> currencies;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final String PATH = "src/repositories/currency.csv";


    public CurrencyRepository() {
            this.currencies = new ArrayList<>();
            readList();
        }

        public void readList() {
            currencies.clear();
        try {
            Scanner reader = new Scanner(new File(PATH));
            if (reader.hasNextLine()) {
                reader.nextLine();
            }

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(";");
                String base_currency = lineScanner.next();
                String quote_currency = lineScanner.next();
                double rate = Double.parseDouble(lineScanner.next().replace(',', '.'));
                LocalDate last_updated = LocalDate.parse(lineScanner.next(),formatter);

                currencies.add(new Currency(base_currency,quote_currency,rate,last_updated));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }



    public List<Currency> getCurrencies() {
        return currencies;
    }
}
