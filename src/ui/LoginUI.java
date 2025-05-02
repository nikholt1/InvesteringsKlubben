package ui;

import utilites.HandleIntInput;
import java.util.Scanner;

public class LoginUI {
    Scanner scanner = new Scanner(System.in);
    private boolean isAdmin;
    private boolean isRunning = true;

    public void printMenu() {
        System.out.println("""
                         -------------------
                        |       LOGIN       |
                         -------------------
                
                  1. User Login
                  2. Admin Login
                  9. Exit application
                
                  Please choose login method:\s""");
        handleMenuChoice();
    }

    public void handleMenuChoice() {
        while (isRunning) {
            int choice = HandleIntInput.handleUserInput(scanner);
            isAdmin = isAdmin(choice);
        }
    }

    public boolean isAdmin(int inputChoice) {
            switch (inputChoice) {
                case 1 -> {
                    isRunning = false;
                    return false;
                }
                case 2 -> {
                    isRunning = false;
                    return true;
                }
                case 9 -> System.exit(0);
                default -> System.out.println("Please enter a valid option");
            }
        return false;
    }

    public String emailCheck() {
        System.out.println("""
                Please enter your email:
                """);
        return scanner.nextLine();
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
}


