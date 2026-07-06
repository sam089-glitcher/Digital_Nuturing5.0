public class InventoryTest {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        // Add Products
        inventory.addProduct(new Product1(101, "Laptop", 20, 55000));
        inventory.addProduct(new Product1(102, "Mouse", 50, 500));
        inventory.addProduct(new Product1(103, "Keyboard", 30, 1200));

        System.out.println("\n===== Inventory =====");
        inventory.displayInventory();

        // Update Product
        System.out.println("===== Update Product =====");
        inventory.updateProduct(102, 75, 650);

        System.out.println("\n===== Inventory After Update =====");
        inventory.displayInventory();

        // Delete Product
        System.out.println("===== Delete Product =====");
        inventory.deleteProduct(103);

        System.out.println("\n===== Inventory After Deletion =====");
        inventory.displayInventory();
    }
}