package services;

import models.Currency;
import repositories.CurrencyRepository;
import java.util.List;

public class CurrencyService {

    //TODO

    // list<Currency>
    // getCurrencyRate()
    // kommunikation til controller

    private CurrencyRepository CurrencyRepository;
    private List<Currency> currencies;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.CurrencyRepository = currencyRepository;
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
            System.out.println("Systemet kunne ikke finde currencyRate");
        }
        return foundRate;
    }
}
