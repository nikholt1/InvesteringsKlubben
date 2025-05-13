package services;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService u;
    @BeforeEach
    void startUp() {
        UserRepository userRepository = new UserRepository();
        u = new UserService(userRepository);
    }
    @AfterEach
    void tearDown() {
        u = null;
    }


    // test findUserData
    @Test
    void findUserData_test_correct_user_found_return_true() {
        //1;Maria Jensen;maria.jensen@email.com;12-04-1990;100000;01-03-2025;01-03-2025
        //TODO
        // User user = user user i assertTrue fra return type User
        assertEquals("User{userID=1, fullName='Maria Jensen', email='maria.jensen@email.com', birthDate=1990-04-12, initCash=100000.0, createdAt=2025-03-01, updateded=2025-03-01}", u.findUserData("maria.jensen@email.com").toString());

    }
    @Test
    void findUserData_test_email_not_in_repository_return_true() {
        // TODO
        // user user = user user i assert>True fra return type user stemmer ikke overens med method return value
        assertEquals(null, u.findUserData("hasbulla@dagestan.rus"));

    }
    //test updateUserCashValue()
    @Test
    void updateUserCashValue_for_existing_user_return_true() {
        assertTrue(u.updateUserCashValue("hasbulla@donesk.ru", 10_000));
    }
    @Test
    void updateUserCashValue_for_non_existing_user_return_false() {
        assertFalse(u.updateUserCashValue("zlatanibrahimovic@sweden.se", 10_000));
    }

    //test writeNewUser()
    @Test
    void writeNewUser_append_new_user_with_correct_minimal_initCash_return_true() {
        assertTrue(u.writeNewUser("King of Mauretania", "atlas@northAfrica.ly", 10_000, "04-08-1997"));
    }

    @Test
    void writeNewUser_append_new_user_with_incorrect_minimal_initCash_return_false() {
        assertFalse(u.writeNewUser("King of Mauretania", "atlas@northAfrica.ly", 9_000, "04-08-1997"));
    }


    // test getUserID
    @Test
    void getUserID_test_correct_id_return_true() {

        assertTrue(u.getUserID("maria.jensen@email.com") == 1);
    }

    @Test
    void getUserID_test_userNotFound_return_true() {
        assertTrue(u.getUserID("hasbulla@dagestan.rus") == -1);
    }


    //userWithdraw()
    @Test
    void userWithdraw_test_successful_withdrawal_return_true() {
        assertTrue(u.userWithdraw(1, 7000));
    }
    @Test
    void userWithdraw_test_not_enough_money_return_False() {
        assertFalse(u.userWithdraw(1, 2_000_000));
    }
//    @Test
//    void userWithdraw_test_invalid_value_input_negative_value_return_true() {
//        assertTrue(u.userWithdraw(100));
//    }

    //userDeposit()
    @Test
    void userDeposit_test_successful_deposit_return_true() {
       assertTrue(u.userDeposit(1,100));
    }
    @Test
    void userDeposit_test_unsuccessful_negativeValue_input() {
        assertFalse(u.userDeposit(1,-100));
    }


    // ComparatorTests
    //sort usersByNameMethod:
    @Test
    void sortUsersByName_functional_Alphabetical_sort_return_true() {
        assertTrue(u.sortUsersByName());
    }
    @Test
    void sortUsersByName_Correct_Alphabetical_sort_return_return_true() {
        // skal være string pga:
        /// expected: models.User@77e9807f<User{userID=5, fullName='Anders Holm', email='anders.holm@email.com', birthDate=1988-05-22, initCash=100000.0, createdAt=2025-03-05, updateded=2025-03-05}> but was: models.User@448ff1a8<User{userID=5, fullName='Anders Holm', email='anders.holm@email.com', birthDate=1988-05-22, initCash=100000.0, createdAt=2025-03-05, updateded=2025-03-05}
        u.sortUsersByName();
        User user1 = new User(
                5,
                "Anders Holm",
                "anders.holm@email.com",
                "22-05-1988",
                100000.0,
                "05-03-2025",
                "05-03-2025"
        );
        List<User> users = u.getAllUsers();
        User actualUser = null;
        actualUser = users.get(0);
        System.out.println(user1);
        System.out.println(actualUser);
        assertEquals(actualUser.toString(), user1.toString());
    }

    @Test
    void sortUsersByName_Incorrect_Alphabetical_sort_return_return_false() {
        u.sortUsersByName();
        User comparableUser = new User(
                5,
                "Zanders Holm",
                "anders.holm@email.com",
                "22-05-1988",
                100000.0,
                "05-03-2025",
                "05-03-2025"
        );
        List<User> users = u.getAllUsers();
        User actualUser = users.get(0);
        System.out.println(comparableUser);
        System.out.println(actualUser);
        assertFalse(actualUser.toString().equals(comparableUser.toString()));
    }

    //SortUsersByCash method:

    @Test
    void sortUsersByCash_functional_CashSort_return_true() {
        assertTrue(u.sortUsersByCash());
    }

    @Test
    void sortUsersByCash_Correct_sort_return_highest_to_lowest_return_true() {
        u.sortUsersByCash();
        User comparableUser = new User(
                11,
                "hasbulla",
                "hasbulla@outsideDagestan.ru",
                "17-06-1993",
                100001.0,
                "07-03-2025",
                "05-05-2025"
        );
        List<User> users = u.getAllUsers();
        User actualUser = users.get(0);
        System.out.println(comparableUser);
        System.out.println(actualUser);
        assertEquals(actualUser.toString(), comparableUser.toString());
    }
    @Test
    void sortUsersByCash_incorrect_sort_return_highest_to_lowest_test_reverse_output_return_false() {
        u.sortUsersByCash();
        User comparableUser = new User(
                1,
                "Maria Jensen",
                "maria.jensen@email.com",
                "12-04-1990",
                100000.0,
                "01-03-2025",
                "01-03-2025"
        );

        List<User> users = u.getAllUsers();
        User actualUser = users.get(0);
        System.out.println(comparableUser);
        System.out.println(actualUser);
        assertFalse(actualUser.toString().equals(comparableUser.toString()));
    }














}