package Models;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private User userId;
    private LocalDate localDate;
    private String ticker;
    private double price;
    private Currency currency;
    private boolean order_type;
    private int quantity;


    public Transaction(int id, User userId, String ticker, LocalDate localDate, double price, Currency currency, boolean order_type, int quantity) {
        this.id = id;
        this.userId = userId;
        this.ticker = ticker;
        this.localDate = localDate;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public boolean isOrder_type() {
        return order_type;
    }

    public void setOrder_type(boolean order_type) {
        this.order_type = order_type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
