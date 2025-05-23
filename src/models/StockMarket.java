package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StockMarket {

    private String ticker;
    private String name;
    private String sector;
    private double price;
    String currency;
    private String rating;
    private double dividend_yield;
    private String market;
    private LocalDate lastUpdate;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public StockMarket(String ticker, String name, String sector, double price, String currency, String rating, double dividend_yield, String market, String lastUpdate) {
        this.ticker = ticker;
        this.name = name;
        this.sector = sector;
        this.price = price;
        this.currency = currency;
        this.rating = rating;
        this.dividend_yield = dividend_yield;
        this.market = market;
        this.lastUpdate = LocalDate.parse(lastUpdate, formatter);
    }

    @Override
    public String toString() {
        return """
                Ticker:     %s
                Name:       %s
                Sector:     %s
                Price:      %.2f
                Currency:   %s
                Rating:     %s
                Dividend:   %.2f
                Market:     %s
                
                (Last Updated:   %s)
                """.formatted(ticker, name, sector, price, currency, rating, dividend_yield, market, lastUpdate);
    }


    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public double getDividend_yield() {
        return dividend_yield;
    }

    public void setDividend_yield(double dividend_yield) {
        this.dividend_yield = dividend_yield;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
