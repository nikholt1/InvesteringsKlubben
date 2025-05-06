package services;

import comparators.ComparatorsortAlphabetical;
import comparators.ComparatoruserSortByCash;
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
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    // writeNewUserData() -> Admin
    public boolean updateUserCashValue(String email, int value) {
        try {
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    userRepository.updateUserCashData(user.getUserID(), value);
                    users = userRepository.getUsers();
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error in repository update of cash value");
            e.printStackTrace();
            return false;
        }
        return false;

    }

    public boolean writeNewUser(String name, String email, double initCash, String birthdate) {
        if (initCash < 10_000) {
            return false;
        }
        try {
            userRepository.writeNewUserToFile(name, email, initCash, birthdate);
            users = userRepository.getUsers();
            return true;
        } catch (Exception e) {
            System.out.println("Error in writeNewUser");
            e.printStackTrace();
            return false;
        }
    }
    //printAllUsers()
    public void printAllUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    //getAllUsers
    public List<User> getAllUsers() {
        return users;
    }

    // sortUsersByName() -> andmin eller alle?
    //TODO
    // make comparator for alphabetical sort
    public boolean sortUsersByName() {
        try {
            users.sort(new ComparatorsortAlphabetical());
            return true;
        } catch (Exception e) {
            System.out.println("Error in sorting names");
            e.printStackTrace();
            return false;
        }
    }

    // sortUsersByCash()
    //TODO
    // make comparator for numeric sort
    public boolean sortUsersByCash() {
        try {
            users.sort(new ComparatoruserSortByCash());
            return true;
        } catch (Exception e) {
            System.out.println("Error in sorting by Cash Value");
            e.printStackTrace();
            return false;
        }
    }

    // getUserID()
    public int getUserID(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user.getUserID();
            }
        }
        System.out.println("User not found in system");
        return -1;
    }




}