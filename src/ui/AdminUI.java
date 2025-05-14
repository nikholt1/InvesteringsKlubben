package ui;

import controllers.AdminController;
import controllers.UserController;
import models.StockMarket;
import utilites.HandleIntInput;

import java.util.List;
import java.util.Scanner;

public class AdminUI {
    private final Scanner scanner = new Scanner(System.in);
    private final AdminController adminController;

    public AdminUI(AdminController adminController) {
        this.adminController = adminController;
    }

    public void start() {
        while (true) {
            printAdminMenu();
            int choice = HandleIntInput.handleUserInput(scanner);
            handleAdminMenuChoice(choice);
        }
    }

    public void printAdminMenu() {
        System.out.println("""
                       -------------------
                      |    ADMIN MENU     |
                       -------------------
                
                1. View stock market
                2. View ranking
                9. Exit application
                
                Please choose an option:""");
    }

    private void handleAdminMenuChoice(int choice) {
        switch (choice) {
            case 1 -> viewStockMarket();
            case 2 -> viewRanking();
            case 9 -> {
                System.out.println("Exiting... ");
                System.exit(0);
            }
            default -> System.out.println("Please enter a valid option.");
        }
    }


    public void viewStockMarket() {
        List<StockMarket> stocks = adminController.getStocks();
        for (StockMarket stockMarket : stocks) {
            System.out.println(stockMarket);
        }
    }

    public void viewRanking() {
        System.out.println("Rangliste af brugers porteføljeværdi");
    }
}
