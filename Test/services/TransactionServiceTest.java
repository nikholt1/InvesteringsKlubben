package services;

import models.StockMarket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.StockMarketRepository;
import repositories.TransactionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    private TransactionService transactionService;
    private StockMarketRepository stockMarketRepository = new StockMarketRepository();
    private StockMarketService stockMarketService = new StockMarketService(stockMarketRepository);
    private List<StockMarket> stockMarkets = stockMarketRepository.getStockMarkets();

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
    void sellStock_successfully() {
        assertTrue(transactionService.sellStock(stockMarkets.get(5), 10));
    }

    @Test
    void sellStock_unSuccessfully() {
        assertFalse(transactionService.sellStock(stockMarkets.get(5), 300));
    }



    @Test
    void getQuantityOfSpecificStockTiedToUser() {
    }




}