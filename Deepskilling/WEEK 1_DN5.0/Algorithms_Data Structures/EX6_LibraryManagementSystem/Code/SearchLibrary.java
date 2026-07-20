import java.util.Arrays;
import java.util.Comparator;

public class LibraryTest {

    public static void main(String[] args) {

        Book[] books = {
                new Book(103, "Java Programming", "James Gosling"),
                new Book(101, "Data Structures", "Mark Allen"),
                new Book(105, "Operating Systems", "Abraham Silberschatz"),
                new Book(102, "Computer Networks", "Andrew Tanenbaum"),
                new Book(104, "Database Systems", "Raghu Ramakrishnan")
        };

        System.out.println("===== Linear Search =====");

        Book result1 = SearchLibrary.linearSearch(books, "Database Systems");

        if (result1 != null) {
            result1.display();
        } else {
            System.out.println("Book not found.");
        }

        // Sort books by title before binary search
        Arrays.sort(books, Comparator.comparing(book -> book.title));

        System.out.println("\n===== Binary Search =====");

        Book result2 = SearchLibrary.binarySearch(books, "Database Systems");

        if (result2 != null) {
            result2.display();
        } else {
            System.out.println("Book not found.");
        }
    }
}