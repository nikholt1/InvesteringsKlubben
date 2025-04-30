package Models;

import java.time.LocalDateTime;

public class Stockmarked {
    private String ticker;
    private String name;
    private String sector;
    private double price;
    private Currency currency;
    private String rating;
    private double dividend_yield;
    private String market;
    private LocalDateTime LastUpdate;
    private String trying; //skal ikke bruges


    public Stockmarked(String ticker, String name, String sector, double price, Currency currency, String rating, double dividend_yield, String market, LocalDateTime lastUpdate) {
        this.ticker = ticker;
        this.name = name;
        this.sector = sector;
        this.price = price;
        this.currency = currency;
        this.rating = rating;
        this.dividend_yield = dividend_yield;
        this.market = market;
        LastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Stockmarked{" +
                "ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", price=" + price +
                ", currency=" + currency +
                ", rating='" + rating + '\'' +
                ", dividend_yield=" + dividend_yield +
                ", market='" + market + '\'' +
                ", LastUpdate=" + LastUpdate +
                '}';
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
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

    public LocalDateTime getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        LastUpdate = lastUpdate;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
