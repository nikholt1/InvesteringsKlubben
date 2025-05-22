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

    public void start(String email) {
        this.userID = getUserID(email);
        user = userService.findUserData(email);
        user.setUserPortfolioData(fetchUserPortfolioValue(userID));
        user.setUserAssetData(fetchUserAssetValue(userID));
        user.setCashAvailableData(fetchUserBalance(userID));
        userUI.start();
    }


    public void buyStock(StockMarket stockMarket, int quantity) {
        boolean failCheck = transactionService.buyStock(stockMarket, quantity, fetchUserBalance(userID));
        if (failCheck) {
            System.out.println("Success buy stock");
        } else {
            System.out.println("Failed buy stock");
        }
    }

    public void sellStock(StockMarket stockMarket, int quantity) {
        transactionService.sellStock(stockMarket, quantity);
    }

    public List<Transaction> getUserStocks() {
        return transactionService.getUserStocksByID(userID);
    }

    public List<StockMarket> getStocks() {
        return stockMarketService.getStocks();
    }

    public StockMarket getSpecificStock(String ticker) {
        return stockMarketService.getSpecificStock(ticker);
    }

    public User findUserData() {
        String email = user.getEmail();
        return userService.findUserData(email);
    }

    public double fetchUserAssetValue(int userID) {
        return transactionService.getUserAssetValue(userID);
    }

    public double fetchUserBalance(int userID) {
        return userService.calculateUserCashBalance(userID, transactionService.calculateSumOfTransactions(userID));
    }

    public double fetchUserPortfolioValue(int userID) {
        return fetchUserAssetValue(userID) + fetchUserBalance(userID);
    }

    public int getUserID(String email) {
        return userService.getUserID(email);
    }
}
