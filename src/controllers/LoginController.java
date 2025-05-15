package controllers;

import repositories.UserRepository;
import services.UserService;
import ui.AdminUI;
import ui.LoginUI;
import ui.UserUI;

public class LoginController {
    private final LoginUI loginUI;
    private AdminController adminController;
    private UserController userController;
    private UserService userService;


    public LoginController() {
        loginUI = new LoginUI(this);
    }

    public void start() {
        loginUI.printMenu();
    }

    public void startUser(String email) {
        userController = new UserController(email);
        userController.start(email);
    }

    public void startAdmin() {
        adminController = new AdminController();
        adminController.start();
    }

    public boolean verifyLogin(String email) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        if (userService.getUserID(email) != -1) {
            return true;
        } else {
            return false;
        }
    }


}
