import controllers.LoginController;
import controllers.UserController;
import models.User;
import repositories.UserRepository;
import services.UserService;
import ui.UserUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LoginController loginController = new LoginController();
        loginController.start();



    }
}
