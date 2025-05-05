package repositories;

import models.StockMarket;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StockMarketRepository {

    //TODO

    // list<StockMarket>

    List<StockMarket> stockMarket;

    //readFile()

    public void readList() {
        try {
            Scanner reader = new Scanner(new File("stockkMarket.csv"));
            while (reader.hasNextInt()) {
                String line = reader.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(";");
                String ticker = lineScanner.nextLine();
                String name = lineScanner.nextLine();
                String sector = lineScanner.nextLine();
                double price = lineScanner.nextDouble();
                String currency = lineScanner.nextLine();
                String rating = lineScanner.nextLine();
                double dividend_yield = lineScanner.nextDouble();
                String market = lineScanner.nextLine();
                LocalDate lastUpdated = LocalDate.parse(lineScanner.nextLine());

                stockMarket.add(new StockMarket(ticker, name, sector, price, currency, rating, dividend_yield, market, lastUpdated));

            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Could not find file");
        }
    }
    //getStockMarket()
    public List<StockMarket> getStockMarket() {
        return stockMarket;
    }

}
