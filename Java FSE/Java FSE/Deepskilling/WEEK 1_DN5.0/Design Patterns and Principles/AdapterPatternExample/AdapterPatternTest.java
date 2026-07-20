public class AdapterPatternTest {

    public static void main(String[] args) {

        System.out.println("=== Adapter Pattern Demo ===\n");

        // Payment through PayPal
        PaymentProcessor paypal = new PayPalAdapter(new PayPalGateway());
        paypal.processPayment(2500);

        // Payment through Stripe
        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());
        stripe.processPayment(4500);
    }

}