package services;

import models.Currency;
import repositories.CurrencyRepository;
import java.util.List;

public class CurrencyService {

    //TODO

    // list<Currency>
    // getCurrencyRate()
    // kommunikation til controller

    private CurrencyRepository currencyRepository;
    private List<Currency> currencies;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
        currencyRepository.readList();
        currencies = currencyRepository.getCurrencies();
    }

    // getCurrencyRate()
    public double getCurrencyRate (double rate) {
        double foundRate = 0;
        for (Currency currency : currencies) {
            if (currency.getRate() == rate) {
                foundRate = currency.getRate();
            }
        }
        if (foundRate == 0) {
            System.out.println("System could not find currency rate");
        }
        return foundRate;
    }

    // currency rate calculation implementation test
    // returner double data type af værdien i DKK
    public double calculateCurrencyToDKK(double DKKValue, String base_currency) {
        currencyRepository.readList();
        double rate = 0.0;
        for (Currency currency : currencies) {
            if (currency.getBase_currency().equals(base_currency)) {
                rate = currency.getRate();
                break;
            }
        }
        double result = DKKValue * rate;
        return result;
    }


    // returner double data type af værdien i base_currency value
    public double calculateCurrencyDKKToCurrency(double DKKValue, String base_currency) {
        currencyRepository.readList();
        double rate = 0.0;
        for (Currency currency : currencies) {
            rate = currency.getRate();
            break;
        }
        double result = DKKValue / rate;
        return 0.0;
    }
}
