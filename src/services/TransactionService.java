package services;

import models.Currency;
import models.StockMarket;
import models.Transaction;
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
    private List<Transaction> transactions;
    private List<Currency> currencies;

    private final StockMarketRepository stockMarketRepository = new StockMarketRepository();
    private StockMarketService stockMarketService = new StockMarketService(stockMarketRepository);
    private final UserRepository userRepository = new UserRepository();
    private UserService userService = new UserService(userRepository);

    private TransactionRepository transactionRepository;
    private int userId;


    public TransactionService(TransactionRepository transactionRepository, int userId) {
        this.transactionRepository = transactionRepository;
        transactionRepository.readList();
        transactions = transactionRepository.getTransactions();
        this.userId = userId;
    }

    public void buyStock(StockMarket stockMarket, int quantity) {
        double price = stockMarket.getPrice();
        double sum = price * quantity;

        if (userService.checkUserCashBalance(userId, sum)) {
            if (writeTransactionToTransactionRepository(stockMarket, "buy", quantity)) {
                userService.userWithdraw(userId, sum);
            }
        }
    }

    public void sellStock(StockMarket stockMarket, int quantity) {
        double stockValue = stockMarket.getPrice();
        double saleValue = stockValue * quantity;

        if (getQuantityOfSpecificStockTiedToUser(stockMarket.getTicker()) < quantity) {
            System.out.println("You do not have enough stocks in " + stockMarket.getTicker());
        }
        try {
            if (writeTransactionToTransactionRepository(stockMarket, "sell", quantity)) {
                userService.userDeposit(userId, saleValue);
            }
        } catch (Exception e) {
            System.out.println("Error in creating transaction");
        }

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
            if (transaction.getTicker() == ticker) {
                quantity += transaction.getQuantity();
            }
        }
        return quantity;
    }

    public List<Transaction> getAllTransactions() {
       return transactionRepository.getTransactions();
    }
}
