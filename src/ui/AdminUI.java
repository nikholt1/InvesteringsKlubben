package ui;

import controllers.AdminController;
import controllers.UserController;
import models.User;
import utilites.HandleIntInput;

import java.util.List;
import java.util.Scanner;

public class AdminUI {
    private final Scanner scanner = new Scanner(System.in);
    private final AdminController adminController;
    private UserController userController;

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
                
                1. View stocks and sectors
                2. View ranking
                3. Create new user
                9. Exit application
                
                Please choose an option:""");
    }

    private void handleAdminMenuChoice(int choice) {
        switch (choice) {
            case 1 -> viewStocksAndSectors();
            case 2 -> viewRanking();
            case 3 -> writeNewUsers();
            case 9 -> {
                System.out.println("Exiting... ");
                System.exit(0);
            }
            default -> System.out.println("Please enter a valid option.");
        }
    }


    public void viewStocksAndSectors() {
        List<String> stocksAndSectors = adminController.getStocksAndSectorsDistribution();
        for (String string : stocksAndSectors) {
            System.out.println(string);
        }
    }

    public void viewRanking() {
        List<User> rankUserList = adminController.getUserPortfoliosSorted();
        int count = 1;
        System.out.printf("\n| %-4s | %-22s | %-18s |\n",
                "Rank", "Full Name", "Portfolio Balance" );
        System.out.println("------------------------------------------------------");
        for (User user : rankUserList) {
            System.out.printf("| #%-3d | %-22s | %-18s |",
                    count,
                    user.getFullName(),
                    user.getUserPortfolioData()
                    );
            count++;
            System.out.println("\n------------------------------------------------------");
        }
    }

    public void writeNewUsers() {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();
        // double balance = 100000;
        System.out.println("Write your birthday in this format DD-MM-YYYY");
        String birthDate = scanner.nextLine();
        //boolean tjek = adminController.writeNewUsers(name,email,balance,birthDate);
        boolean tjek = adminController.writeNewUsers(name,email,birthDate);
        if (tjek) {
            System.out.println("User created");
        } else {
            System.out.println("Error in creating user");
        }
    }
}
