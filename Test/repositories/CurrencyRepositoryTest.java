package repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CurrencyRepositoryTest {

    private CurrencyRepository c;
    @BeforeEach
    void startUp() {
        c = new CurrencyRepository();
    }
    @AfterEach
    void tearDown() {
        c = null;
    }



    @Test
    void calculateCurrencyToDKK_test_correctReturn_return_true() {
        assertEquals(c.calculateCurrencyDKKToCurrency(1, "EUR"), 7,45);
    }



}