package repositories;

import models.StockMarket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockMarketRepository {

    List<StockMarket> stockMarkets;
    private final String PATH = "src/repositories/stockMarket.csv";

    public StockMarketRepository() {
        this.stockMarkets = new ArrayList<>();
        readList();
    }

    public void readList() {
        stockMarkets.clear();
        try {
            Scanner reader = new Scanner(new File(PATH));
            if (reader.hasNextLine()) {
                reader.nextLine();
            }
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(";");

                String ticker = lineScanner.next();
                String name = lineScanner.next();
                String sector = lineScanner.next();
                double price = Double.parseDouble(lineScanner.next().replace(',', '.'));
                String currency = lineScanner.next();
                String rating = lineScanner.next();
                double dividend_yield = Double.parseDouble(lineScanner.next().replace(',', '.'));
                String market = lineScanner.next();
                String lastUpdate = lineScanner.next();

                stockMarkets.add(new StockMarket(ticker, name, sector, price, currency, rating, dividend_yield, market, lastUpdate));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public List<StockMarket> getStockMarkets() {
        return stockMarkets;
    }
}
