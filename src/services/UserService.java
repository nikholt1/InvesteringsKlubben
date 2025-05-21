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

    // Method to initialize user balance based on transactions and init_cash from CSV
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

    //findUserData()
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

    // writeNewUserData() -> Admin
    /*
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

     */

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

    //userDeposit()
    /*
    public boolean userDeposit(int userID, double value) {
        if (value < 0) {
            System.out.println("Error in deposit: Value must be over 0");
            return false;
        }
        try {
            return userRepository.depositToAccount(userID, value);

        } catch (Exception e) {
            System.out.println("Error in deposit");
            e.printStackTrace();
            return false;
        }
    }

     */



    /*
    //userWithdraw()
    public boolean userWithdraw(int userID, double value) {
        if (value < 0) {
            System.out.println("Error in withdrawal: Value must be under 0");
            return false;
        }
        try {
            return userRepository.withdrawFromAccount(userID, value);

        } catch (Exception e) {
            System.out.println("Error in withdrawal");
            e.printStackTrace();
            return false;
        }
    }

     */
    /*
    public boolean checkUserCashBalance(int userID, double value) {
        try {
            if (calculateUserCashBalance(userID, value)) {

            };
        } catch (Exception e) {
            System.out.println("Error in fetching balance");
            e.printStackTrace();
            return false;
        }
    }

     */




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