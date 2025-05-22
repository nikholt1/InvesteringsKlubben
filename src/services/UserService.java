package services;

import comparators.ComparatorsortAlphabetical;
import comparators.ComparatoruserSortByCash;
import models.User;
import repositories.UserRepository;
import java.util.List;

public class UserService {

    private List<User> users;
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        userRepository.readFile();
        users = userRepository.getUsers();
    }

    public double calculateUserCashBalance(int userId, double sumOfTransactions) {
        double userBalance = 0.0;

        for (User user : users) {
            if (user.getUserID() == userId) {
                userBalance = user.getINIT_CASH() + sumOfTransactions;
            }
        }
        return userBalance;
    }

    public double calculateUserPortfolioValue(int userId) {
        double userPortfolioValue = 0.0;

        for (User user : users) {
            if (user.getUserID() == userId) {
                userPortfolioValue = user.getINIT_CASH();
            }
        }

        return userPortfolioValue;
    }

    public User findUserData(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }


    public User findUserByID(int userID) {
        for (User user : users) {
            if (user.getUserID() == userID) {
                System.out.println(user);
                return user;
            }
        }
        System.out.println("User not found");
        return null;
    }

    public boolean writeNewUser(String name, String email, String birthdate) {
        try {
            userRepository.writeNewUserToFile(name, email, birthdate);
            users = userRepository.getUsers();
            return true;
        } catch (Exception e) {
            System.out.println("Error in creating user");
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