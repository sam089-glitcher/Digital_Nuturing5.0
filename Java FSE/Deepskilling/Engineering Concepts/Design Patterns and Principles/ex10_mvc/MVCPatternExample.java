// ==================== Student.java (Model) ====================
public class Student {
    private String name;
    private String id;
    private String grade;

    public Student(String id, String name, String grade) {
        this.id    = id;
        this.name  = name;
        this.grade = grade;
    }

    // Getters
    public String getId()    { return id; }
    public String getName()  { return name; }
    public String getGrade() { return grade; }

    // Setters
    public void setId(String id)       { this.id = id; }
    public void setName(String name)   { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }
}

// ==================== StudentView.java (View) ====================
class StudentView {

    public void displayStudentDetails(String name, String id, String grade) {
        System.out.println("╔══════════════════════════╗");
        System.out.println("║      Student Details      ║");
        System.out.println("╠══════════════════════════╣");
        System.out.printf( "║  ID    : %-17s║%n", id);
        System.out.printf( "║  Name  : %-17s║%n", name);
        System.out.printf( "║  Grade : %-17s║%n", grade);
        System.out.println("╚══════════════════════════╝");
    }

    public void displayMessage(String message) {
        System.out.println("[View] " + message);
    }
}

// ==================== StudentController.java (Controller) ====================
class StudentController {
    private Student  model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view  = view;
    }

    // Getters - fetch from model
    public String getStudentName()  { return model.getName(); }
    public String getStudentId()    { return model.getId(); }
    public String getStudentGrade() { return model.getGrade(); }

    // Setters - update model
    public void setStudentName(String name) {
        view.displayMessage("Updating name to: " + name);
        model.setName(name);
    }

    public void setStudentId(String id) {
        view.displayMessage("Updating ID to: " + id);
        model.setId(id);
    }

    public void setStudentGrade(String grade) {
        view.displayMessage("Updating grade to: " + grade);
        model.setGrade(grade);
    }

    // Trigger view to display current model data
    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}

// ==================== MVCTest.java (Main Class) ====================
class MVCTest {
    public static void main(String[] args) {

        System.out.println("=== MVC Pattern Demo ===\n");

        // Step 1: Create Model (data)
        Student student = new Student("STU-001", "Alice Johnson", "A+");

        // Step 2: Create View
        StudentView view = new StudentView();

        // Step 3: Create Controller - links model and view
        StudentController controller = new StudentController(student, view);

        // Step 4: Display initial data
        System.out.println("--- Initial Student Details ---");
        controller.updateView();

        System.out.println();

        // Step 5: Update data via controller (not directly on model)
        System.out.println("--- Updating Student Details ---");
        controller.setStudentName("Alice Johnson-Smith");
        controller.setStudentGrade("A");

        System.out.println();

        // Step 6: Display updated data
        System.out.println("--- Updated Student Details ---");
        controller.updateView();

        System.out.println();

        // Step 7: Demonstrate second student
        System.out.println("--- Second Student ---");
        Student student2       = new Student("STU-002", "Bob Kumar", "B+");
        StudentController ctrl2 = new StudentController(student2, view);
        ctrl2.updateView();
    }
}
