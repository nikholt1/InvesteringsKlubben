package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private int id;
    private int userId;
    private LocalDate localDate;
    private String ticker;
    private double price;
    private String currency;
    private String order_type;
    private int quantity;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Transaction(int id, int userId, String localDate, String ticker, double price, String currency, String order_type, int quantity) {
        this.id = id;
        this.userId = userId;
        this.localDate = LocalDate.parse(localDate, formatter);
        this.ticker = ticker;
        this.price = price;
        this.currency = currency;
        this.order_type = order_type;
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", localDate=" + localDate +
                ", ticker='" + ticker + '\'' +
                ", price=" + price +
                ", currency=" + currency +
                ", order_type=" + order_type +
                ", quantity=" + quantity +
                '}';
    }

    public double getFullPrice() {
        return price * quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
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

    public String isOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_type() {
        return order_type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
