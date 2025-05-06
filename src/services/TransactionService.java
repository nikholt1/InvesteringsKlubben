package services;

import models.Currency;
import models.StockMarket;
import models.Transaction;
import repositories.StockMarketRepository;
import repositories.TransactionRepository;

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

    private TransactionRepository transactionRepository;
    private int userId;


    public TransactionService(TransactionRepository transactionRepository, int userId) {
        this.transactionRepository = transactionRepository;
        transactionRepository.readList();
        transactions = transactionRepository.getTransactions();
        this.userId = userId;
    }

    public boolean writeTransactionToTransactionRepository(String ticker, String buyOrSell, int quantity) {
        for (StockMarket sm : stockMarketService.getStockMarketRepository().getStockMarkets()) {
            System.out.println("Loaded ticker: " + sm.getTicker());
        }
        try {
            StockMarket chosenStockMarket = null;
            for (StockMarket stockMarket : stockMarketService.getStockMarketRepository().getStockMarkets()) {
                if (stockMarket.getTicker().equalsIgnoreCase(ticker)) {
                    chosenStockMarket = stockMarket;
                }
            }
            if (chosenStockMarket != null) {
                transactionRepository.writeNewTransactionToFile(userId, chosenStockMarket.getTicker(), chosenStockMarket.getPrice(), buyOrSell, quantity);
            } else {
                return false;
            }
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

    public void getAllTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
