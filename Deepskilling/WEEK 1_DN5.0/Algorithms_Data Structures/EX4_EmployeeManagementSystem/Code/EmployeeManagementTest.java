public class EmployeeManagementTest {

    public static void main(String[] args) {

        EmployeeManagement management = new EmployeeManagement(5);

        // Add Employees
        management.addEmployee(new Employee(101, "Rahul", "Manager", 70000));
        management.addEmployee(new Employee(102, "Priya", "Developer", 55000));
        management.addEmployee(new Employee(103, "Amit", "Tester", 45000));

        System.out.println("\n===== Employee List =====");
        management.traverseEmployees();

        System.out.println("\n===== Search Employee =====");
        management.searchEmployee(102);

        System.out.println("\n===== Delete Employee =====");
        management.deleteEmployee(102);

        System.out.println("\n===== Employee List After Deletion =====");
        management.traverseEmployees();
    }
}