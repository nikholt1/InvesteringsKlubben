package ui;

import controllers.UserController;
import models.StockMarket;
import models.Transaction;
import utilites.HandleIntInput;

import java.util.List;
import java.util.Scanner;

public class UserUI {
    private final Scanner scanner = new Scanner(System.in);
    private final UserController userController;
    private int userID = 1;

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

    //private boolean isMainMenuRunning = true;
    //private boolean isStockMarketRunning = true;
    // controller logic? skal vi have én instantiation i main, og parse objects ned.
    // eller, lave "new" instantiation af flere objekter igennem programmet.
//    public UserUI(int userID) {
//        this.userID = userID;
//        this.userController = new UserController(userID);
//    }
    /*
    // Usermuliheder:
    user Menu >> valgmuligheder
    - stockmarket lise
    - userBased transaction history
    - user account information

    */
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
                System.out.println("Exiting... Goodbye!");
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
        System.out.println("Dine transaktioner:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public void viewAccount() {
        System.out.println("din profil:");
        System.out.println(userController.findUserData());
    }


    public void viewStockMarket() {
        List<StockMarket> stocks = userController.getStocks();
        for (StockMarket stockMarket : stocks) {
            System.out.println(stockMarket);
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


