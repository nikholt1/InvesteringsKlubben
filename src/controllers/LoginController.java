package controllers;

import services.UserService;
import ui.AdminUI;
import ui.LoginUI;

public class LoginController {
    LoginUI loginUI;
    AdminController adminController;
    UserController userController;
    UserService userService;

    public LoginController() {
        this.loginUI = new LoginUI();
    }

    public void start() {
        loginUI.printMenu();
        navigateTo();
    }

    public void navigateTo() {
        if (loginUI.getIsAdmin()) {
            // adminController.start();
        } else {
            // userController.start();
            verifyLogin();
        }
    }

    public void verifyLogin() {
        //  User user = userService.checkIfUserExists(loginUI.emailCheck());
        /*
        if (user != null) {
            userController.start(User user);
        } else {
            sout: "Could not find user";
        }
         */
    }


}
