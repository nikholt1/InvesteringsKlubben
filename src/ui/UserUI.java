package ui;

import controllers.UserController;
import models.StockMarket;
import models.Transaction;
import models.User;
import utilites.HandleIntInput;

import java.util.List;
import java.util.Scanner;

public class UserUI {
    private final Scanner scanner = new Scanner(System.in);
    private final UserController userController;
    private int userID = 1;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public UserUI(UserController userController) {
        this.userController = userController;
    }

    public void start() {
        while (true) {
            printUserMenu();
            int choice = HandleIntInput.handleUserInput(scanner);
            handleUserMenuChoice(choice);
        }
    }

    public void printUserMenu() {
        System.out.println("""
                       -------------------
                      |       MENU        |
                       -------------------
                
                1. View stock market
                2. View transaction history
                3. View account
                9. Exit application
                
                Please choose an option:""");
    }

    private void handleUserMenuChoice(int choice) {
        switch (choice) {
            case 1 -> runStockMarketMenu();
            case 2 -> viewTransactionHistory();
            case 3 -> viewAccount();
            case 9 -> {
                System.out.println("Exiting... ");
                System.exit(0);
            }
            default -> System.out.println("Please enter a valid option.");
        }
    }

    private void runStockMarketMenu() {
        boolean inStockMarket = true;
        while (inStockMarket) {
            printStockMarketMenu();
            int choice = HandleIntInput.handleUserInput(scanner);
            inStockMarket = handleStockMarketChoice(choice);
        }
    }

    private void printStockMarketMenu() {
        System.out.println("""
                       -------------------
                      |   STOCK MARKET    |
                       -------------------
                
                1. View stocks
                2. Buy stock
                3. Sell stock
                9. Return to main menu
                
                Please choose an option:""");
    }

    private boolean handleStockMarketChoice(int choice) {
        switch (choice) {
            case 1 -> viewStockMarket();
            case 2 -> buyStock();
            case 3 -> sellStock();
            case 9 -> {
                return false;
            }
            default -> System.out.println("Please enter a valid option.");
        }
        return true;
    }

    private void viewTransactionHistory() {
        List<Transaction> transactions = userController.getUserStocks();

        System.out.println("""       
                                        ---------------------------
                                       |    TRANSACTION HISTORY    |
                                        ---------------------------
                    """);

        // HEADER
        System.out.printf("| %-3s | %-10s | %-8s | %-4s | %-6s | %-8s | %-12s |\n",
                "ID", "Date", "Stock", "Type", "Amount", "Rate", "Value");
        System.out.println("------------------------------------------------------------------------");

        for (Transaction transaction : transactions) {
            String orderType = transaction.getOrder_type().equals("buy") ? "Buy" : "Sell";
            String color = transaction.getOrder_type().equals("buy") ? ANSI_RED : ANSI_GREEN;

            System.out.printf("\n| %-3d | %-10s | %-8s | %-4s | %-5d | %-8.2f | %s%-12s%s |\n",
                    transaction.getId(),
                    transaction.getLocalDate(),
                    transaction.getTicker(),
                    orderType,
                    transaction.getQuantity(),
                    transaction.getPrice(),
                    color, transaction.getFullPrice(), ANSI_RESET
                    );
            System.out.println("\n------------------------------------------------------------------------");
        }
    }

    public void viewAccount() {
        User user = userController.findUserData();
        double cashBalance = userController.fetchUserBalance(user.getUserID());
        double valueInAssets = userController.fetchUserAssetValue(user.getUserID());
        double totalPortfolioValue = userController.fetchUserPortfolioValue(user.getUserID());

        System.out.printf("""
                       ------------------
                      |      PROFILE     |
                       ------------------
                
                User ID:        %d
                Name:           %s
                Email:          %s
                Date of birth:  %s
                
                Cash available:         %.2f
                Value in assets:        %.2f
                
                Total Portfolio value:  %.2f
                
                \n
                """, user.getUserID(), user.getFullName(), user.getEmail(), user.getBirthDate(), cashBalance, valueInAssets, totalPortfolioValue);
    }


    public void viewStockMarket() {
        List<StockMarket> stocks = userController.getStocks();

        System.out.println("""       
                                                             ---------------------
                                                            |   MARKET OVERVIEW   |
                                                             ---------------------
                    """);
        // ticker;name;sector;price;currency;rating;dividend_yield;market;last_updated
        // HEADER
        System.out.printf("| %-8s | %-22s | %-14s | %-10s | %-8s | %-8s | %-8s | %-18s | %-16s |\n",
                "Ticker", "Name", "Sector", "Price", "Currency", "Rating", "Dividend", "Market", "Last Updated");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");

        for (StockMarket stock : stocks) {


            System.out.printf("\n| %-8s | %-22s | %-14s | %-10.2f | %-6s | %-10s | %-8.2f | %-18s | %-16s |\n",
                    stock.getTicker(),
                    stock.getName(),
                    stock.getSector(),
                    stock.getPrice(),
                    stock.getCurrency(),
                    stock.getRating(),
                    stock.getDividend_yield(),
                    stock.getMarket(),
                    stock.getLastUpdate()
            );
            System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void buyStock() {

        viewStockMarket();
        System.out.println("Which market would you like to buy stock from? Enter here: ");
        String chosenTicker = scanner.nextLine();
        StockMarket stock = userController.getSpecificStock(chosenTicker);
        System.out.println(stock);
        System.out.println("How many stocks would you like to buy? Enter here: ");
        int choiceQty = scanner.nextInt();
        scanner.nextLine();

        userController.buyStock(stock, choiceQty);
    }

    public void sellStock() {
        List<Transaction> userStocks = userController.getUserStocks();
        System.out.println("Your stocks: ");
        for (Transaction transaction : userStocks) {
            System.out.println(transaction);
        }
        System.out.println("Which stock would you like to sell: ");
        String chosenTicker = scanner.nextLine();
        StockMarket stock = userController.getSpecificStock(chosenTicker);
        System.out.println("What amount would you like to sell: ");
        int choiceQty = scanner.nextInt();
        scanner.nextLine();

        userController.sellStock(stock, choiceQty);
    }
}


