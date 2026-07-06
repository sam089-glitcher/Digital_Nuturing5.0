public class ProxyPatternTest {

    public static void main(String[] args) {

        System.out.println("=== Proxy Pattern Demo ===\n");

        Image image = new ProxyImage("nature.jpg");

        System.out.println("First Display:");
        image.display();

        System.out.println();

        System.out.println("Second Display:");
        image.display();
    }

}