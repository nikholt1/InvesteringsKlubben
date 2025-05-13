package controllers;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserControllerTest {

    //TODO
    /// Integrated tests

    UserController c;


    @BeforeEach
    void setUp() {
        c = new UserController();
    }

    @AfterEach
    void tearDown() {
        c = null;
    }


    @Test
    void test_getCurrencyByRate() {
        
    }




}