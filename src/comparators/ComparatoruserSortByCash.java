package comparators;

import controllers.UserController;
import models.User;

import java.util.Comparator;

public class ComparatoruserSortByCash implements Comparator<User> {
    private UserController userController;
    @Override
    public int compare(User u1, User u2) {
        return Double.compare(userController.fetchUserPortfolioValue(u2.getUserID()), userController.fetchUserPortfolioValue(u1.getUserID()));
    }
}
