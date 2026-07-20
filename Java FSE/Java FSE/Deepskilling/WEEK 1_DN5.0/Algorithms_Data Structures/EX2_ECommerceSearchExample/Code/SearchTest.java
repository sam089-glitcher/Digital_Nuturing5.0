import java.util.Arrays;
import java.util.Comparator;

public class SearchTest {

    public static void main(String[] args) {

        Product[] products = {
                new Product(103, "Laptop", "Electronics"),
                new Product(101, "Shoes", "Fashion"),
                new Product(105, "Phone", "Electronics"),
                new Product(102, "Watch", "Accessories"),
                new Product(104, "Headphones", "Electronics")
        };

        System.out.println("===== Linear Search =====");

        Product result1 = SearchAlgorithms.linearSearch(products, 104);

        if (result1 != null) {
            result1.display();
        } else {
            System.out.println("Product not found.");
        }

        // Sort array before Binary Search
        Arrays.sort(products, Comparator.comparingInt(p -> p.productId));

        System.out.println("\n===== Binary Search =====");

        Product result2 = SearchAlgorithms.binarySearch(products, 104);

        if (result2 != null) {
            result2.display();
        } else {
            System.out.println("Product not found.");
        }
    }
}