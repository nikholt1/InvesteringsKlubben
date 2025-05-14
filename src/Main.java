import controllers.LoginController;
import models.User;
import repositories.TransactionRepository;
import repositories.UserRepository;
import services.TransactionService;
import services.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LoginController loginController = new LoginController();
        loginController.start();





    }
}
