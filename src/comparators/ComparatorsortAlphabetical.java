package comparators;

import models.User;

import java.util.Comparator;

public class ComparatorsortAlphabetical implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.getFullName().compareTo(o2.getFullName());
    }
}
