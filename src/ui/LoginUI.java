package ui;

import controllers.LoginController;
import utilites.HandleIntInput;
import java.util.Scanner;

public class LoginUI {
    private Scanner scanner = new Scanner(System.in);
    private boolean isAdmin;
    private boolean isUser;
    private boolean isRunning = true;
    private LoginController loginController;

    public LoginUI(LoginController loginController) {
        this.loginController = loginController;
     }


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
            if (choice == 1) {
                String email = emailCheck();
                while (!loginController.verifyLogin(email)) {
                    email = emailCheck();
                }
                loginController.startUser(email);
            } else if (choice == 2) {
                loginController.startAdmin();
            }

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


