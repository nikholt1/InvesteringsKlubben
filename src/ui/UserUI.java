package ui;

import utilites.HandleIntInput;

import java.util.Scanner;

public class UserUI {
    Scanner scanner = new Scanner(System.in);
    private boolean isMainMenuRunning = true;
    private boolean isStockMarketRunning = true;

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
                    //viewTransactionHistory();

                }
                case 3 -> {
                    isMainMenuRunning = false;
                    //viewAccount();
                }
                case 9 -> System.exit(0);
                default -> System.out.println("Please enter a valid option");
            }
        }
    }


    public void printStockMarketMenu() {
        System.out.println("""
                       -------------------
                      |    STOCK MARKET   |
                       -------------------
                
                1. View tickers
                2. Buy stock
                3. Sell stock
                9. Exit application
                
                Please choose login method:\s""");
        handleStockMarketChoice();
    }

    public void handleStockMarketChoice() {
        while (isStockMarketRunning) {
            int choice = HandleIntInput.handleUserInput(scanner);
            switch (choice) {
                case 1 -> {
                    isStockMarketRunning = false;
                    //viewStockMarket();
                }
                case 2 -> {
                    isStockMarketRunning = false;
                    //buyStock();
                }
                case 3 -> {
                    isStockMarketRunning = false;
                    //sellStock();
                }
                case 9 -> System.exit(0);
                default -> System.out.println("Please enter a valid option");
            }
        }
    }

    public void viewStockMarket() {
        // userController.getStockMarket();
    }



}
