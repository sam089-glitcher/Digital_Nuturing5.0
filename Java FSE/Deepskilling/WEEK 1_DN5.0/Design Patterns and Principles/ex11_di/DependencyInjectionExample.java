import java.util.HashMap;
import java.util.Map;

// ==================== Customer.java (Model) ====================
class Customer {
    private String id;
    private String name;
    private String email;

    public Customer(String id, String name, String email) {
        this.id    = id;
        this.name  = name;
        this.email = email;
    }

    public String getId()    { return id; }
    public String getName()  { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Customer{id='" + id + "', name='" + name + "', email='" + email + "'}";
    }
}

// ==================== CustomerRepository.java (Repository Interface) ====================
interface CustomerRepository {
    Customer findCustomerById(String id);
    void     saveCustomer(Customer customer);
    void     deleteCustomer(String id);
}

// ==================== CustomerRepositoryImpl.java (Concrete Repository) ====================
class CustomerRepositoryImpl implements CustomerRepository {

    // Simulated in-memory database
    private Map<String, Customer> database = new HashMap<>();

    public CustomerRepositoryImpl() {
        // Pre-populate with sample data
        database.put("C001", new Customer("C001", "Alice Johnson", "alice@example.com"));
        database.put("C002", new Customer("C002", "Bob Smith",    "bob@example.com"));
        database.put("C003", new Customer("C003", "Carol White",  "carol@example.com"));
        System.out.println("[Repository] CustomerRepositoryImpl initialized with 3 customers.");
    }

    @Override
    public Customer findCustomerById(String id) {
        System.out.println("[Repository] Looking up customer with ID: " + id);
        Customer customer = database.get(id);
        if (customer == null) {
            System.out.println("[Repository] Customer not found: " + id);
        }
        return customer;
    }

    @Override
    public void saveCustomer(Customer customer) {
        database.put(customer.getId(), customer);
        System.out.println("[Repository] Customer saved: " + customer);
    }

    @Override
    public void deleteCustomer(String id) {
        Customer removed = database.remove(id);
        if (removed != null)
            System.out.println("[Repository] Customer deleted: " + id);
        else
            System.out.println("[Repository] Customer not found for deletion: " + id);
    }
}

// ==================== CustomerService.java (Service Class) ====================
class CustomerService {

    // Dependency declared as interface - not as concrete class
    private final CustomerRepository customerRepository;

    // Constructor Injection - dependency passed from outside
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        System.out.println("[Service] CustomerService created with " +
                           customerRepository.getClass().getSimpleName());
    }

    public Customer getCustomerById(String id) {
        System.out.println("[Service] Request to get customer: " + id);
        Customer customer = customerRepository.findCustomerById(id);
        if (customer != null) {
            System.out.println("[Service] Found: " + customer);
        }
        return customer;
    }

    public void addCustomer(String id, String name, String email) {
        Customer newCustomer = new Customer(id, name, email);
        System.out.println("[Service] Adding new customer: " + newCustomer);
        customerRepository.saveCustomer(newCustomer);
    }

    public void removeCustomer(String id) {
        System.out.println("[Service] Removing customer: " + id);
        customerRepository.deleteCustomer(id);
    }
}

// ==================== DependencyInjectionTest.java (Main) ====================
public class DependencyInjectionExample {
    public static void main(String[] args) {

        System.out.println("=== Dependency Injection Demo ===\n");

        // Step 1: Create the concrete repository
        CustomerRepository repository = new CustomerRepositoryImpl();

        // Step 2: Inject repository INTO service via constructor (DI)
        CustomerService service = new CustomerService(repository);

        System.out.println();

        // Step 3: Use the service
        System.out.println("--- Fetching Customers ---");
        service.getCustomerById("C001");
        System.out.println();
        service.getCustomerById("C003");
        System.out.println();
        service.getCustomerById("C999"); // not found

        System.out.println();

        System.out.println("--- Adding New Customer ---");
        service.addCustomer("C004", "David Brown", "david@example.com");

        System.out.println();

        System.out.println("--- Fetching Newly Added Customer ---");
        service.getCustomerById("C004");

        System.out.println();

        System.out.println("--- Removing Customer ---");
        service.removeCustomer("C002");
        service.getCustomerById("C002"); // should be not found now

        System.out.println();
        System.out.println("=== DI Benefit: Service depends on interface, not impl ===");
        System.out.println("    Easily swap CustomerRepositoryImpl with a mock or DB-backed version.");
    }
}
