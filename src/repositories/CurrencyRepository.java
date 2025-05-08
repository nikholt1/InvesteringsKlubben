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
                double rate = Double.parseDouble(lineScanner.next().replace(',', '.'));
                LocalDate last_updated = LocalDate.parse(lineScanner.next(),formatter);

                currencies.add(new Currency(base_currency,quote_currency,rate,last_updated));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("CurrencyRepository.readlist().Systemet kunne ikke finde Valuta-filen");
        }
    }

    //getCurrency()
    public Currency findCurrency (String base_currency) {
        Currency result = null;
        for (Currency Currency : currencies) {
            if(Currency.getBase_currency().equals(base_currency)) {
                result = Currency;
            }
        }
        if (result == null) {
            System.out.println("CurrencyRepository.findCurrency().Systemet kunne ikke finde valuta");
        }
        return result;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

}
