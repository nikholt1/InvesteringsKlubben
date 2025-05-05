package services;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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








}