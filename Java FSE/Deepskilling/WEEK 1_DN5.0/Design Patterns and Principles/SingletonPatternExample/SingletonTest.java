public class SingletonTest {

    public static void main(String[] args) {

        // Get the first Logger instance
        Logger logger1 = Logger.getInstance();
        logger1.log("Application started.");

        // Get the second Logger instance
        Logger logger2 = Logger.getInstance();
        logger2.log("Loading user data.");

        // Compare both references
        if (logger1 == logger2) {
            System.out.println("\nOnly one Logger instance exists.");
        } else {
            System.out.println("\nMore than one Logger instance exists.");
        }

        // Print hash codes
        System.out.println("Logger1 HashCode: " + logger1.hashCode());
        System.out.println("Logger2 HashCode: " + logger2.hashCode());
    }
}