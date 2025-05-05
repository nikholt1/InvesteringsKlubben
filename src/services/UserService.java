package services;

import models.User;
import repositories.UserRepository;
import java.util.List;

public class UserService {

    private List<User> users;
    private UserRepository userRepository;

    //TODO

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        userRepository.readFile();
        users = userRepository.getUsers();
    }

    //findUserData()
    public User findUserData(String email) {
        User foundUser = null;
        for (User user : users) {
            try {
                foundUser = userRepository.findUserMyEmail(email);
                return foundUser;
            } catch (Exception e) {
                System.out.println("User not found");
            }
        }
        return null;
    }

    // writeNewUserData() -> Admin

    //findUserByEmail()

    // sortUsersByName() -> andmin eller alle?

    // sortUsersByCash()

    // getUserID()
    public int getUserID(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println(user.getUserID());
                return user.getUserID();
            }
        }
        System.out.println("User not found in system");
        return -1;
    }
}