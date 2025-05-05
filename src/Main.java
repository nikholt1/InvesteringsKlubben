import controllers.LoginController;
import models.User;
import repositories.UserRepository;
import services.UserService;

public class Main {
    public static void main(String[] args) {
        LoginController loginController = new LoginController();
        loginController.start();

        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        System.out.println(userService.getUserID("maria.jensen@email.com"));




    }
}
