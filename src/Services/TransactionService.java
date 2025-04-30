package Services;

public class TransactionService {

    //TODO
    // Format "id;user_id;date;ticker;price;currency;order_type;quantity"
    //Transaction er dependent på StockMarket
    // relevant information fra StockMarket er Ticker / Price / currency
    // Constructor modtager User info i Parametre

    // List<Currency> -> henter currency Rate fra CurrencyService
    // List<StockMarked> -> henter fra StockMarketService

    // findStockByNameAndGetPrice() -> altid sidst opdaterede værdi fra StockMarket liste

    // calculatePriceFromRateWithQuantity()

    // calculateRateToDKK()

    // writeTransactionToTransactionRepository() -> eksempel format: 1;1;01-03-2025;NOVO-B;710,5;DKK;buy;20


    // viewUserTransactionHistory(); -> enhanced for loop igennem transaction list -> printer kun UserID ekvivalent data beregn samlet DKK

    // getAllTransactions();

}
