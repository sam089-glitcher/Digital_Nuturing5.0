import java.util.HashMap;

public class Inventory {

    private HashMap<Integer, Product1> inventory = new HashMap<>();

    // Add Product
    public void addProduct(Product1 product) {
        inventory.put(product.productId, product);
        System.out.println("Product added successfully.");
    }

    // Update Product
    public void updateProduct(int productId, int quantity, double price) {

        Product1 product = inventory.get(productId);

        if (product != null) {
            product.quantity = quantity;
            product.price = price;
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete Product
    public void deleteProduct(int productId) {

        if (inventory.remove(productId) != null) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Display Inventory
    public void displayInventory() {

        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        for (Product1 product : inventory.values()) {
            product.display();
        }
    }
}