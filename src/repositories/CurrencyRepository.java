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

    //TODO

    // list<Currency>

    //readList()

    //getCurrency

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
            // Skips the header describing the format
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

    //getCurrency()
    public Currency findCurrency (String base_currency) {
        readList();
        Currency result = null;
        for (Currency Currency : currencies) {
            if(Currency.getBase_currency().equals(base_currency)) {
                result = Currency;
            }
        }
        if (result == null) {
            System.out.println("Currency not found");
        }
        return result;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }




}
