package services;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.TransactionRepository;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    private TransactionService transactionService;

    @BeforeEach
    void startUp() {
        TransactionRepository transactionRepository = new TransactionRepository();
        transactionService = new TransactionService(transactionRepository, 1);
    }

    @AfterEach
    void tearDown() {
        transactionService = null;
    }

    @Test
    void writeTransactionToTransactionRepository_successful() {
        assertTrue(transactionService.writeTransactionToTransactionRepository("DANSKE", "buy", 10));
    }

    @Test
    void writeTransactionToTransactionRepository_not_successful() {
        assertFalse(transactionService.writeTransactionToTransactionRepository("Bella", "Sell", 2));
    }

    @Test
    void viewUserTransactionHistory() {
    }
}