package repositories;

import models.Currency;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class CurrencyRepository {

    //TODO

    // list<Currency>

    //readList()

    //getCurrency

        ArrayList<Currency> currencies;
        
        public CurrencyRepository() {
            this.currencies = new ArrayList<>();
            readList();
        }


        public void readList() {
        try {
            Scanner reader = new Scanner(new File("src/repositories/currency.csv"));
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(";");
                String base_currency = lineScanner.next();
                String quote_currency = lineScanner.next();
                double rate = Double.parseDouble(lineScanner.next());
                LocalDate last_updated = LocalDate.parse(lineScanner.next());

                currencies.add(new Currency(base_currency,quote_currency,rate,last_updated));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Could not find file");
        }
    }




}
