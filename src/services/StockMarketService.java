package services;

import models.StockMarket;
import repositories.StockMarketRepository;

import java.util.List;

public class StockMarketService {

    private StockMarketRepository stockMarketRepository;
    private List<StockMarket> stockMarkets;

    //TODO
    public StockMarketService(StockMarketRepository stockMarketRepository) {
        this.stockMarketRepository = stockMarketRepository;
        stockMarketRepository.readList();
        stockMarkets = stockMarketRepository.getStockMarkets();
    }


    // getStockMarket()
    public StockMarketRepository getStockMarketRepository() {
        return stockMarketRepository;
    }

    public List<StockMarket> getStocks() {
        return stockMarkets;
    }

    public StockMarket getSpecificStock(String ticker) {
        StockMarket foundStock = null;
        for (StockMarket stockMarket : stockMarkets) {
            if (stockMarket.getTicker().equals(ticker)) {
                foundStock = stockMarket;
                System.out.println(foundStock);
                return foundStock;
            }
        }
        System.out.println("Did not find stock");
        return null;
    }

    // getTicker()
    public String getTicker(String ticker) {
        String foundTicker = " ";
        for (StockMarket stockMarket : stockMarkets) {
            if (stockMarket.getTicker().equals(ticker)) {
                foundTicker = stockMarket.getTicker();
                return foundTicker;
            }
            else return "Ticker not found";
        }
        return null;
    }

    // getPrice()
    public double getPrice(Double price) {
        double foundPrice = 0;
        for (StockMarket stockMarket : stockMarkets) {
            if (stockMarket.getPrice() == price) {
                foundPrice = stockMarket.getPrice();
            }
            else return foundPrice;
        }
        return foundPrice;
    }

    // getCurrency()
    public String getCurrency() {
        String foundCurrency = " ";
        for (StockMarket stockMarket : stockMarkets) {
            foundCurrency = stockMarket.getCurrency();
        }
        return foundCurrency;
    }

}
