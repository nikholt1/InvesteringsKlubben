package comparators;

import models.User;

import java.util.Comparator;

public class ComparatoruserSortByCash implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {
        return Double.compare(u2.getINIT_CASH(), u1.getINIT_CASH());
    }
}
