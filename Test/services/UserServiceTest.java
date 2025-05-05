package services;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.UserRepository;

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

    }
    @Test
    void findUserData_test_email_not_in_repository_return_true() {
        // TODO
        // user user = user user i assert>True fra return type user stemmer ikke overens med method return value
    }

    // test getUserID
    @Test
    void getUserID_test_correct_id_return_true() {
        assertTrue(u.getUserID("maria.jensen@email.com") == 1);
    }







}