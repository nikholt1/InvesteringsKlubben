package controllers;

import models.StockMarket;
import models.Transaction;
import models.User;
import repositories.CurrencyRepository;
import repositories.StockMarketRepository;
import repositories.TransactionRepository;
import repositories.UserRepository;
import services.CurrencyService;
import services.StockMarketService;
import services.TransactionService;
import services.UserService;
import ui.UserUI;

import java.util.List;

public class UserController {

    //TODO
    /// add attributes, Constructor getters and setters

    private StockMarketRepository stockMarketRepository;
    private CurrencyRepository currencyRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    private CurrencyService currencyService;
    private StockMarketService stockMarketService;
    private TransactionService transactionService;
    private UserService userService;

    private UserUI userUI;

    private int userID;
    private User user;

//    public UserController(int userID, CurrencyService currencyService,
//                          StockMarketService stockMarketService,
//                          TransactionService transactionService,
//                          UserService userService) {
//        this.userID = userID;
//        this.currencyService = currencyService;
//        this.stockMarketService = stockMarketService;
//        this.transactionService = transactionService;
//        this.userService = userService;
//    }
    public UserController(String email) {
        this.stockMarketRepository = new StockMarketRepository();
        this.currencyRepository = new CurrencyRepository();
        this.transactionRepository = new TransactionRepository();
        this.userRepository = new UserRepository();
        this.userService = new UserService(userRepository);
        this.userID = getUserID(email);

        this.stockMarketService = new StockMarketService(stockMarketRepository);
        this.currencyService = new CurrencyService(currencyRepository);
        this.transactionService = new TransactionService(transactionRepository, userID); // mangler implementering


        this.userUI = new UserUI(this);


    }



    /// start method()
    public void start(String email) {
        this.userID = getUserID(email);
        user = userService.findUserData(email);
        userUI.start();
    }

    public void start(User user) {
        this.user = user;
    }


    // fetch currencyService Data
    public double getCurrencyByRate(double rate) {
        return currencyService.getCurrencyRate(rate);
    }

    public double calculateCurrencyToDKK(double DKKValue, String base_currency) {
        return currencyService.calculateCurrencyToDKK(DKKValue, base_currency);
    }

    public double calculateCurrencyDKKToCurrency(double DKKValue, String base_currency) {
        return currencyService.calculateCurrencyDKKToCurrency(DKKValue, base_currency);
    }

    // fetch transactionService Data

    public void buyStock(StockMarket stockMarket, int quantity) {
        boolean failCheck = transactionService.buyStock(stockMarket, quantity);
        if (failCheck) {
            System.out.println("Purchase completed");
        } else {
            System.out.println("Error during purchase");
        }

    }
    public void sellStock(StockMarket stockMarket, int quantity) {
        transactionService.sellStock(stockMarket, quantity);
    }

    public void viewUserTransactionHistory() {
//        transactionService.viewUserTransactionHistory();
    }
    public List<Transaction> getUserStocks() {
        return transactionService.getUserStocksByID(userID);
    }
    public int getQuantityOfSpecificStockTiedToUser(String ticker) {
//        return transactionService.getQuantityOfSpecificStockTiedToUser(ticker);
        return -1;
    }



    // fetch stockMarketService Data
    public String getTicker(String ticker) {
        return stockMarketService.getTicker(ticker);
    }
    public double getPrice(Double price) {
        return stockMarketService.getPrice(price);
    }
    public String getStockMarketCurrency() {
        return stockMarketService.getCurrency();
    }
    public StockMarketRepository getStockMarketRepository() {
        return stockMarketService.getStockMarketRepository();
    }
    public List<StockMarket> getStocks() {
        return stockMarketService.getStocks();
    }

    public StockMarket getSpecificStock(String ticker) {
        return stockMarketService.getSpecificStock(ticker);
    }

    public double getPortfolioBalance() {
        User currentUser = transactionService.getUserPortfolioData(userID);
        return currentUser.getBalance();
    }


    // fetch userService Data
    public User findUserData() {
        String email = user.getEmail();
        return userService.findUserData(email);
    }
    public boolean updateUserCashValue(String email, int value) {
        return userService.updateUserCashValue(email, value);
    }
                                    /// springer writeNewUser over, fordi det kun er admin privilege

    public boolean userWithdraw(double value) {
        return userService.userWithdraw(userID, value);
    }

    public boolean checkUserCashBalance(double value) {
        return userService.checkUserCashBalance(userID, value);
    }


    public int getUserID(String email) {
        return userService.getUserID(email);              /// måske redundant
    }

    /// implement navigation functionality between service class and UI
    /// switch call methods from services.



}
