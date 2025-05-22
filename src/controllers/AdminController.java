package controllers;

import com.sun.management.UnixOperatingSystemMXBean;
import models.StockMarket;
import models.User;
import repositories.CurrencyRepository;
import repositories.StockMarketRepository;
import repositories.TransactionRepository;
import repositories.UserRepository;
import services.CurrencyService;
import services.StockMarketService;
import services.TransactionService;
import services.UserService;
import ui.AdminUI;

import java.util.List;

public class AdminController {

    private StockMarketRepository stockMarketRepository;
    private CurrencyRepository currencyRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    private CurrencyService currencyService;
    private StockMarketService stockMarketService;
    private TransactionService transactionService;
    private UserService userService;


    private AdminUI adminUI;

    public AdminController() {
        this.stockMarketRepository = new StockMarketRepository();
        this.currencyRepository = new CurrencyRepository();
        this.transactionRepository = new TransactionRepository();
        this.userRepository = new UserRepository();


        this.stockMarketService = new StockMarketService(stockMarketRepository);
        this.currencyService = new CurrencyService(currencyRepository);
        this.transactionService = new TransactionService(transactionRepository,1);
        this.userService = new UserService(userRepository);

        this.adminUI = new AdminUI(this);
    }



    public void start() {
        adminUI.start();
    }


    public boolean writeNewUsers(String name, String email, String birthDate) {
        return userService.writeNewUser(name, email, birthDate);
    }


    public List<User> getUserPortfoliosSorted() {
        return transactionService.getUserPortfoliosSorted();
    }

    public List<String> getStocksAndSectorsDistribution() {
        return transactionService.getStocksSectorDistribution();
    }
}
