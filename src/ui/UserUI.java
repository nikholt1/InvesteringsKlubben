package ui;

import controllers.UserController;
import models.StockMarket;
import models.Transaction;
import utilites.HandleIntInput;

import java.util.List;
import java.util.Scanner;

public class UserUI {
    Scanner scanner = new Scanner(System.in);
    private boolean isMainMenuRunning = true;
    private boolean isStockMarketRunning = true;
    private int userID;
    private UserController userController;


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
                      |        MENU       |
                       -------------------
                
                1. View stock market
                2. View transaction history
                3. View account
                9. Exit application
                
                Please choose login method:\s""");
        handleUserMenuChoice();
    }

    public void handleUserMenuChoice() {
        while (isMainMenuRunning) {
            int choice = HandleIntInput.handleUserInput(scanner);
            switch (choice) {
                case 1 -> {
                    isMainMenuRunning = false;
                    printStockMarketMenu();
                }
                case 2 -> {
                    isMainMenuRunning = false;
                    viewTransactionHistory();

                }
                case 3 -> {
                    isMainMenuRunning = false;
                    viewAccount();
                }
                case 9 -> System.exit(0);
                default -> System.out.println("Please enter a valid option");
            }
        }
    }


    public void viewTransactionHistory() {
        List<Transaction> userTransactions = userController.getUserStocks();
        System.out.println("Dine transaktioner");
        for (Transaction transaction : userTransactions) {
            System.out.println(transaction);
        }
    }

    public void viewAccount() {
        System.out.println(userController.findUserData());
    }


    /*
    // Usermuliheder:
    stockmarket menu
    - se stocks
    - købe stocks
    - sælge stocks
    --- > måske return to main menu


    */
    public void printStockMarketMenu() {
        System.out.println("""
                       -------------------
                      |    STOCK MARKET   |
                       -------------------
                
                1. View stocks
                2. Buy stock
                3. Sell stock
                9. Return to main menu
                
                Please choose login method:\s""");
        handleStockMarketChoice();
    }

    public void handleStockMarketChoice() {
        while (isStockMarketRunning) {
            int choice = HandleIntInput.handleUserInput(scanner);
            switch (choice) {
                case 1 -> {
                    isStockMarketRunning = false;
                    viewStockMarket();
                }
                case 2 -> {
                    isStockMarketRunning = false;
                    buyStock();
                }
                case 3 -> {
                    isStockMarketRunning = false;
                    sellStock();
                }
                case 9 -> isStockMarketRunning = false;
                default -> System.out.println("Please enter a valid option");
            }
        }
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
