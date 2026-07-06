public class OrderTest {

    public static void main(String[] args) {

        Order[] orders = {
                new Order(101, "Rahul", 4500),
                new Order(102, "Priya", 1200),
                new Order(103, "Amit", 8500),
                new Order(104, "Sneha", 3000),
                new Order(105, "Rohan", 6500)
        };

        System.out.println("===== Original Orders =====");
        SortOrders.displayOrders(orders);

        // Bubble Sort
        SortOrders.bubbleSort(orders);

        System.out.println("===== Orders After Bubble Sort =====");
        SortOrders.displayOrders(orders);

        // New array for Quick Sort
        Order[] orders2 = {
                new Order(101, "Rahul", 4500),
                new Order(102, "Priya", 1200),
                new Order(103, "Amit", 8500),
                new Order(104, "Sneha", 3000),
                new Order(105, "Rohan", 6500)
        };

        SortOrders.quickSort(orders2, 0, orders2.length - 1);

        System.out.println("===== Orders After Quick Sort =====");
        SortOrders.displayOrders(orders2);
    }
}