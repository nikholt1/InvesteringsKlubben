package models;

import java.time.LocalDate;

public class Currency {

    private String base_currency;
    private String quote_Currency;
    private double rate;
    private LocalDate Last_update;

    public Currency(String base_currency, String quote_Currency, double rate, LocalDate last_update) {
        this.base_currency = base_currency;
        this.quote_Currency = quote_Currency;
        this.rate = rate;
        Last_update = last_update;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "base_currency='" + base_currency + '\'' +
                ", quote_Currency='" + quote_Currency + '\'' +
                ", rate=" + rate +
                ", Last_update=" + Last_update +
                '}';
    }

    public String getBase_currency() {
        return base_currency;
    }

    public void setBase_currency(String base_currency) {
        this.base_currency = base_currency;
    }

    public String getQuote_Currency() {
        return quote_Currency;
    }

    public void setQuote_Currency(String quote_Currency) {
        this.quote_Currency = quote_Currency;
    }

    public double getRate() {
        return rate;
    }
}


