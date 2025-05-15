package services;

import comparators.ComparatoruserSortByCash;
import models.Currency;
import models.StockMarket;
import models.Transaction;
import models.User;
import repositories.CurrencyRepository;
import repositories.StockMarketRepository;
import repositories.TransactionRepository;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    private final List<Transaction> transactions;

    private final StockMarketRepository stockMarketRepository = new StockMarketRepository();
    private final StockMarketService stockMarketService = new StockMarketService(stockMarketRepository);
    private final UserRepository userRepository = new UserRepository();
    private final UserService userService = new UserService(userRepository);
    private final CurrencyRepository currencyRepository = new CurrencyRepository();
    private final CurrencyService currencyService = new CurrencyService(currencyRepository);

    private final TransactionRepository transactionRepository;
    private final int userId;


    public TransactionService(TransactionRepository transactionRepository, int userId) {
        this.transactionRepository = transactionRepository;
        transactionRepository.readList();
        transactions = transactionRepository.getTransactions();
        this.userId = userId;
    }

    public boolean buyStock(StockMarket stockMarket, int quantity) {
        double price = stockMarket.getPrice();
        double sum = price * quantity;
        double convertedSum = currencyService.calculateCurrencyToDKK(sum, stockMarket.getCurrency());

        if (userService.checkUserCashBalance(userId, convertedSum)) {
            if (writeTransactionToTransactionRepository(stockMarket, "buy", quantity)) {
                return userService.userWithdraw(userId, convertedSum);
            }
        }
        return false;
    }

    public boolean sellStock(StockMarket stockMarket, int quantity) {
        double stockValue = stockMarket.getPrice();
        double saleValue = stockValue * quantity;
        double convertedSaleValue = currencyService.calculateCurrencyToDKK(saleValue, stockMarket.getCurrency());


        int userStockQuantity = checkUserStockQuantity(stockMarket, quantity);
//        if (getQuantityOfSpecificStockTiedToUser(stockMarket.getTicker()) < quantity) {
//            System.out.println("You do not have enough stocks in " + stockMarket.getTicker());
//            return false;
//        }
        if (userStockQuantity < quantity) {
            System.out.println("not enough stocks");
            return false;
        }
        try {
            if (writeTransactionToTransactionRepository(stockMarket, "sell", quantity)) {
                userService.userDeposit(userId, convertedSaleValue);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in creating transaction");
            return false;
        }
        return false;
    }
    public int checkUserStockQuantity(StockMarket stockMarket, int quantity) {
        List<Transaction> userTransactions = getUserStocksByID(userId);
        int actualQTY = 0;
        for (Transaction transaction : userTransactions) {
            if (transaction.getTicker().equals(stockMarket.getTicker())) {
                if (transaction.getOrder_type().equals("buy")) {
                    actualQTY += transaction.getQuantity();
                } else if (transaction.getOrder_type().equals("sell")) {
                    actualQTY -= transaction.getQuantity();
                }
            }
        }
        return actualQTY;
    }

    public boolean writeTransactionToTransactionRepository(StockMarket stockMarket, String buyOrSell, int quantity) {
        try {
            transactionRepository.writeNewTransactionToFile(userId, stockMarket.getTicker(), stockMarket.getPrice(), buyOrSell, quantity);
            return true;
        } catch (Exception e) {
            System.out.println("Error in creating transaction");
            return false;
        }
    }

    public void viewUserTransactionHistory() {
        double totalSpent = 0.0;
        for (Transaction transaction : transactions) {
            if (transaction.getUserId() == userId) {
                totalSpent += transaction.getPrice();
                System.out.println(transaction);
            }
        }

        System.out.println("You have spent " + totalSpent + " since becoming a member");
    }

    public List<Transaction> getUserStocks() {
        List<Transaction> userTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getUserId() == userId) {
                System.out.println(userId);
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
    }
    public List<Transaction> getUserStocksByID(int userID) {
        List<Transaction> userTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getUserId() == userID) {
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
    }

    //getAllUsersPortfolioData() •Kunne se en samlet oversigt over alle brugeres porteføljeværdi
    public List<User> getAllUserPortfolioData() {
        List<User> updatedPortfolioList = new ArrayList<>();
        List<User> AllUsers = userService.getAllUsers();
        int userID = 0;
        for (User user : AllUsers) {
            updatedPortfolioList.add(getUsersDataAndUpdatePortfolioData(userID));
            userID++;
        }
        return updatedPortfolioList;
    }

    //getUsersDataAndUpdatePortfolioData()
    public User getUsersDataAndUpdatePortfolioData(int userID) {
        User user = userService.findUserByID(userID);
        List<Transaction> usersTransactions = getUserStocksByID(userID);
        double transactionsSum = 0.0;
        for (Transaction transaction : usersTransactions) {
            int QTY = transaction.getQuantity();
            String ticker = transaction.getTicker();
            StockMarket stock = stockMarketService.getSpecificStock(ticker);
            double priceOverQTY = stock.getPrice() * QTY;
            double userBalance = user.getBalance();
            System.out.println(userBalance);
            user.setBalance(userBalance + priceOverQTY);
        }
        return user;
    }

    //getRankedUserByPortfolioBaseList() •Kunne få præsenteret en rangliste over hvem der klarer sig bedst
    public List<User> getRankedUserByPortfolioBaseList() {
        List<User> updatedPortfolioList = getAllUserPortfolioData();
        updatedPortfolioList.sort(new ComparatoruserSortByCash());
        return updatedPortfolioList;
    }

    //getStocksAndSectorsDistribution()
    //•Få vist fordelinger på aktier og sektorer
    public List<String> getStocksAndSectorsDistribution() {
        List<String> stockDistribution = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getOrder_type().equals("buy")) {
                int QTY = transaction.getQuantity();
                StockMarket stock = stockMarketService.getSpecificStock(transaction.getTicker());
                if (stockDistribution.contains(stock.getTicker())) {

                }
                stockDistribution.add(stock.getTicker() + " " + stock.getName() + " " + stock.getSector() + " " + String.valueOf(QTY) + " " + "bought");
            } else if (transaction.getOrder_type().equals("sell")) {
                int QTY = transaction.getQuantity();
                StockMarket stock = stockMarketService.getSpecificStock(transaction.getTicker());
                stockDistribution.add(stock.getTicker() + " " + stock.getName() + " " + stock.getSector() + " " + String.valueOf(QTY) + " " + "sold");
            }
        }
        return stockDistribution;
    }

    public List<String> getStocksSectorDistribution() {
        List<StockMarket> stockMarkets = stockMarketService.getStocks();
        List<String> stockDistribution = new ArrayList<>();
        String sector = " ";
        String ticker = " ";
        int QTY = 0;
        double sum = 0.0;
        for (StockMarket stockMarket : stockMarkets) {
            QTY = 0;
            for (Transaction transaction : transactions) {
                if (stockMarket.getTicker().equals(transaction.getTicker())) {
                    if (transaction.getOrder_type().equals("buy")) {
                        QTY += transaction.getQuantity();
                    } else if (transaction.getOrder_type().equals("sell")) {
                        QTY -= transaction.getQuantity();
                    }
                }
            }
            sum = stockMarket.getPrice() * QTY;
            stockDistribution.add("Stock = " + stockMarket.getTicker() + " Quantity = " + QTY + " Standing volume = " + sum );
        }
        List<String> processedSectors = new ArrayList<>();
        for (StockMarket stockMarket : stockMarkets) {
            sector = stockMarket.getSector();
            if (processedSectors.contains(sector)) {
                continue;
            }
            processedSectors.add(sector);
            QTY = 0;
            sum = 0;


            for (StockMarket stockMarket1 : stockMarkets) {
                if (stockMarket1.getSector().equals(sector)) {
                    int tickerQty = 0;
                    for (Transaction transaction : transactions) {
                        if (transaction.getTicker().equals(stockMarket1.getTicker())) {
                            if (transaction.getOrder_type().equals("buy")) {
                                tickerQty += transaction.getQuantity();
                            } else if (transaction.getOrder_type().equals("sell")) {
                                tickerQty -= transaction.getQuantity();
                            }
                        }
                    }
                    QTY += tickerQty;
                    sum += stockMarket1.getPrice() * tickerQty;
                }
            }

            stockDistribution.add("Sector: " + sector + " Quantity = " + QTY + " Standing volume = " + sum );
        }
        return stockDistribution;
    }











    public int getQuantityOfSpecificStockTiedToUser(String ticker) {
        List<Transaction> userTransactions = getUserStocks();
        int quantity = 0;

        for (Transaction transaction : userTransactions) {
            if (transaction.getTicker().equals(ticker)) {
                quantity += transaction.getQuantity();
            }
        }
        return quantity;
    }

    public List<Transaction> getAllTransactions() {
       return transactionRepository.getTransactions();
    }
}
