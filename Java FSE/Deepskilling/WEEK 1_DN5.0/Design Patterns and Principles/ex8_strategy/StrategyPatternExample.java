// ==================== PaymentStrategy.java (Strategy Interface) ====================
public interface PaymentStrategy {
    void pay(double amount);
}

// ==================== CreditCardPayment.java (Concrete Strategy 1) ====================
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolder;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cardHolder, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card");
        System.out.println("  Card Holder : " + cardHolder);
        System.out.println("  Card Number : " + maskCard(cardNumber));
    }

    private String maskCard(String number) {
        return "****-****-****-" + number.substring(number.length() - 4);
    }
}

// ==================== PayPalPayment.java (Concrete Strategy 2) ====================
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal");
        System.out.println("  PayPal Account: " + email);
    }
}

// ==================== UPIPayment.java (Concrete Strategy 3) ====================
class UPIPayment implements PaymentStrategy {
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using UPI");
        System.out.println("  UPI ID: " + upiId);
    }
}

// ==================== PaymentContext.java (Context Class) ====================
class PaymentContext {
    private PaymentStrategy paymentStrategy;

    // Strategy can be set via constructor or changed at runtime
    public PaymentContext(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    // Allows switching strategy at runtime
    public void setPaymentStrategy(PaymentStrategy strategy) {
        System.out.println("Switching payment method to: " + strategy.getClass().getSimpleName());
        this.paymentStrategy = strategy;
    }

    // Execute the current strategy
    public void executePayment(double amount) {
        System.out.println("\n[Checkout] Processing payment of $" + amount + "...");
        paymentStrategy.pay(amount);
        System.out.println("[Checkout] Payment completed.\n");
    }
}

// ==================== StrategyTest.java ====================
class StrategyTest {
    public static void main(String[] args) {

        System.out.println("=== Strategy Pattern Demo ===\n");

        // Define strategies
        PaymentStrategy creditCard = new CreditCardPayment("1234567890123456", "John Doe", "123");
        PaymentStrategy paypal     = new PayPalPayment("john.doe@gmail.com");
        PaymentStrategy upi        = new UPIPayment("johndoe@upi");

        // Start with Credit Card
        PaymentContext cart = new PaymentContext(creditCard);
        cart.executePayment(499.99);

        // Switch to PayPal at runtime
        cart.setPaymentStrategy(paypal);
        cart.executePayment(89.00);

        // Switch to UPI
        cart.setPaymentStrategy(upi);
        cart.executePayment(1200.00);
    }
}
