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

    // These constants are used to represent negative and positive values, they color strings
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
                                            --------------------
                                           |    KØBSHISTORIK    |
                                            --------------------
                    """);

        // HEADER
        System.out.printf("| %-3s | %-10s | %-8s | %-4s | %-5s | %-8s | %-12s |\n",
                "ID", "Dato", "Aktie", "Type", "Antal", "Kurs", "Beløb");
        System.out.println("------------------------------------------------------------------------");

        for (Transaction transaction : transactions) {
            String orderType = transaction.getOrder_type().equals("buy") ? "Køb" : "Salg";
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
        System.out.printf("""
                       ------------------
                      |    MIN PROFIL    |
                       ------------------
                
                Bruger ID:      %d
                Navn:           %s
                Email:          %s
                Fødselsdato:    %s
                \n
                """, user.getUserID(), user.getFullName(), user.getEmail(), user.getBirthDate());
    }


    public void viewStockMarket() {
        List<StockMarket> stocks = userController.getStocks();

        System.out.println("""       
                                                             ---------------------
                                                            |   Markedsoversigt   |
                                                             ---------------------
                    """);
        // ticker;name;sector;price;currency;rating;dividend_yield;market;last_updated
        // HEADER
        System.out.printf("| %-8s | %-22s | %-14s | %-10s | %-6s | %-10s | %-8s | %-18s | %-16s |\n",
                "Aktie", "Navn", "Sektor", "Pris", "Valuta", "Bedømmelse", "Dividend", "Market", "Sidst Opdateret");
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

        System.out.println("Hvilken stock vil du gerne købe: ");
        int choiceStock = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Hvilket antal af de værdi papirer vil du gerne købe: ");
        int choiceQty = scanner.nextInt();
        scanner.nextLine();

//        userController.buyStock();

    }

    public void sellStock() {
        List<Transaction> userStocks = userController.getUserStocks();
        System.out.println("Din beholdning: ");
        for (Transaction transaction : userStocks) {
            System.out.println(transaction);
        }
        System.out.println("Hvilken stock vil du gerne sælge: ");
        int choiceStock = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Hvilket antal af de værdi papirer vil du gerne sælge: ");
        int choiceQty = scanner.nextInt();
        scanner.nextLine();

//        userController.sellStock();
    }
}


