package services;

import comparators.ComparatoruserSortByCash;
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

    public boolean buyStock(StockMarket stockMarket, int quantity, double cashBalance) {
        double price = stockMarket.getPrice();
        double sum = price * quantity;
        double convertedSum = currencyService.calculateCurrencyToDKK(sum, stockMarket.getCurrency());

        if (cashBalance >= convertedSum) {
            if (writeTransactionToTransactionRepository(stockMarket, "buy", quantity)) {
                return true;
            }
        }
        return false;
    }

    public boolean sellStock(StockMarket stockMarket, int quantity) {

        int userStockQuantity = checkUserStockQuantity(stockMarket, quantity);

        if (userStockQuantity < quantity) {
            System.out.println("not enough stocks");
            return false;
        }
        try {
            if (writeTransactionToTransactionRepository(stockMarket, "sell", quantity)) {
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





    public List<Transaction> getUserStocksByID(int userID) {
        List<Transaction> userTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getUserId() == userID) {
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
    }



    public double getUserAssetValue(int userId) {
        User user = userService.findUserByID(userId);
        List<Transaction> userTransactions = getUserStocksByID(user.getUserID());

        double stockValue = 0.0;

        for (Transaction transaction : userTransactions) {

            if (transaction.getOrder_type().equals("buy")) {
                stockValue += stockMarketService.getSpecificStock(transaction.getTicker()).getPrice() * transaction.getQuantity();
            }

            if (transaction.getOrder_type().equals("sell")) {
                stockValue -= stockMarketService.getSpecificStock(transaction.getTicker()).getPrice() * transaction.getQuantity();
            }
        }
        return stockValue;
    }


    public double calculateSumOfTransactions(int userId) {
        List<Transaction> userTransactions = getUserStocksByID(userId);

        double transactionSum = 0.0;

        for (Transaction transaction : userTransactions) {
            int quantity = transaction.getQuantity();

            if (transaction.getOrder_type().equals("buy")) {
                transactionSum -= transaction.getPrice() * quantity;
            }

            if (transaction.getOrder_type().equals("sell")) {
                transactionSum += transaction.getPrice() * quantity;
            }
        }
        return transactionSum;
    }



    public List<User> getUserPortfoliosSorted() {
        List<User> userPortfoliosSorted = userService.getAllUsers();
        for (User user : userPortfoliosSorted) {
            user.setUserPortfolioData(getUserAssetValue(user.getUserID()) + + userService.calculateUserCashBalance(user.getUserID(), calculateSumOfTransactions(user.getUserID())));
        }
        userPortfoliosSorted.sort(new ComparatoruserSortByCash());
        return userPortfoliosSorted;
    }

    public List<String> getStocksDistribution() {
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
            stockDistribution.add(String.format("%-10s | %-8d | %10f", stockMarket.getTicker(), QTY, sum));

        }

        return stockDistribution;
    }
    public List<String> getSectorDistribution() {
        List<StockMarket> stockMarkets = stockMarketService.getStocks();
        List<String> stockDistribution = new ArrayList<>();
        String sector = " ";
        String ticker = " ";
        int QTY = 0;
        double sum = 0.0;
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

            stockDistribution.add(String.format("%-17s | %-10d | %10f", sector, QTY, sum));
        }
        return stockDistribution;
    }
}
