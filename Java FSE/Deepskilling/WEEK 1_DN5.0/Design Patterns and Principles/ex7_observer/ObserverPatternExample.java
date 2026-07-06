import java.util.ArrayList;
import java.util.List;

// ==================== Observer.java (Observer Interface) ====================
interface Observer {
    void update(String stockSymbol, double price);
}

// ==================== Stock.java (Subject Interface) ====================
interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}

// ==================== StockMarket.java (Concrete Subject) ====================
class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private String stockSymbol;
    private double stockPrice;

    public StockMarket(String stockSymbol, double initialPrice) {
        this.stockSymbol = stockSymbol;
        this.stockPrice  = initialPrice;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Observer registered: " + observer.getClass().getSimpleName());
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer deregistered: " + observer.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        System.out.println("\n[StockMarket] Notifying " + observers.size() + " observer(s) about " +
                           stockSymbol + " @ $" + stockPrice);
        for (Observer observer : observers) {
            observer.update(stockSymbol, stockPrice);
        }
    }

    // Triggers notification when price changes
    public void setStockPrice(double newPrice) {
        System.out.println("\n[StockMarket] Price changed: " + stockSymbol +
                           " $" + stockPrice + " → $" + newPrice);
        this.stockPrice = newPrice;
        notifyObservers();
    }

    public double getStockPrice() { return stockPrice; }
    public String getStockSymbol() { return stockSymbol; }
}

// ==================== MobileApp.java (Concrete Observer 1) ====================
class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("[MobileApp - " + appName + "] ALERT: " +
                           stockSymbol + " is now $" + price);
    }
}

// ==================== WebApp.java (Concrete Observer 2) ====================
class WebApp implements Observer {
    private String website;

    public WebApp(String website) {
        this.website = website;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("[WebApp - " + website + "] UPDATE: " +
                           stockSymbol + " price updated to $" + price);
    }
}

// ==================== EmailAlert.java (Concrete Observer 3) ====================
class EmailAlert implements Observer {
    private String email;

    public EmailAlert(String email) {
        this.email = email;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("[EmailAlert → " + email + "] Stock " +
                           stockSymbol + " changed to $" + price);
    }
}

// ==================== ObserverTest.java ====================
public class ObserverPatternExample {
    public static void main(String[] args) {

        System.out.println("=== Observer Pattern Demo ===\n");

        // Create the subject
        StockMarket appleStock = new StockMarket("AAPL", 180.00);

        // Create observers
        MobileApp  robinhoodApp  = new MobileApp("Robinhood");
        MobileApp  zerodhaApp    = new MobileApp("Zerodha");
        WebApp     yahooFinance  = new WebApp("finance.yahoo.com");
        EmailAlert investorEmail = new EmailAlert("investor@gmail.com");

        System.out.println("--- Registering Observers ---");
        appleStock.registerObserver(robinhoodApp);
        appleStock.registerObserver(zerodhaApp);
        appleStock.registerObserver(yahooFinance);
        appleStock.registerObserver(investorEmail);

        // Price changes - all observers notified
        appleStock.setStockPrice(185.50);
        appleStock.setStockPrice(190.00);

        // Deregister one observer
        System.out.println("\n--- Deregistering zerodhaApp ---");
        appleStock.deregisterObserver(zerodhaApp);

        // Only remaining observers get notified
        appleStock.setStockPrice(178.25);
    }
}
