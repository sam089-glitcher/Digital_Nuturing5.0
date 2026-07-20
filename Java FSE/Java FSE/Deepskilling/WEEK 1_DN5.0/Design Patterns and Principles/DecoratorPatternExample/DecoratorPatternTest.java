public class DecoratorPatternTest {

    public static void main(String[] args) {

        System.out.println("=== Decorator Pattern Demo ===\n");

        // Email only
        Notifier emailNotifier = new EmailNotifier();

        System.out.println("Email Notification:");
        emailNotifier.send("Your order has been placed.");

        System.out.println();

        // Email + SMS
        Notifier smsNotifier = new SMSNotifierDecorator(new EmailNotifier());

        System.out.println("Email + SMS Notification:");
        smsNotifier.send("Your payment was successful.");

        System.out.println();

        // Email + SMS + Slack
        Notifier allNotifier = new SlackNotifierDecorator(
                                    new SMSNotifierDecorator(
                                        new EmailNotifier()));

        System.out.println("Email + SMS + Slack Notification:");
        allNotifier.send("Your package has been delivered.");
    }

}