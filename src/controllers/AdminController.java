package controllers;

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

    public List<StockMarket> getStocks() {
        return stockMarketService.getStocks();
    }

    /// start method()
    public void start() {
        adminUI.start();
    }

    /*
    Klubbens lederskal:
•Kunne se en samlet oversigt over alle brugeres porteføljeværdi
•Kunne få præsenteret en rangliste over hvem der klarer sig bedst
•Få vist fordelinger på aktier og sektorer
    */

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public boolean writeNewUsers(String name, String email, double balance, String birthDate) {
        return userService.writeNewUser(name, email, balance, birthDate);
                //11;King of Mauretania;atlas@northAfrica.ly;04-08-1997;10000.0;06-05-2025;06-05-2025;
    }




    /// implement navigation functionality between service class and UI

    public List<User> getAllUserPortfolioData() {
        return transactionService.getAllUserPortfolioData();
    }

    public User getUsersDataAndUpdatePortfolioData(int userID) {
        return transactionService.getUserPortfolioData(userID);
    }

    public List<User> getRankedUserByPortfolioBaseList() {
        return transactionService.getRankedUserByPortfolioBaseList();
    }

    public List<String> getStocksAndSectorsDistribution() {
        return transactionService.getStocksSectorDistribution();
    }

    /// switch call methods from services.


    ///

}
