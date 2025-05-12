package services;

import models.Currency;
import models.StockMarket;
import models.Transaction;
import repositories.CurrencyRepository;
import repositories.StockMarketRepository;
import repositories.TransactionRepository;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    //TODO
    // Format "id;user_id;date;ticker;price;currency;order_type;quantity"
    //Transaction er dependent på StockMarket
    // relevant information fra StockMarket er Ticker / Price / currency
    // Constructor modtager User info i Parametre

    // List<Currency> -> henter currency Rate fra CurrencyService
    // List<StockMarked> -> henter fra StockMarketService

    // findStockByNameAndGetPrice() -> altid sidst opdaterede værdi fra StockMarket liste

    // calculatePriceFromRateWithQuantity()

    // calculateRateToDKK()

    // getAllTransactions();
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

        if (getQuantityOfSpecificStockTiedToUser(stockMarket.getTicker()) <= quantity) {
            System.out.println("You do not have enough stocks in " + stockMarket.getTicker());
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
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
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
