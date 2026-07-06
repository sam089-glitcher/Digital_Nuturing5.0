public class TaskManagementTest {

    public static void main(String[] args) {

        TaskLinkedList taskList = new TaskLinkedList();

        // Add Tasks
        taskList.addTask(101, "Design Database", "Pending");
        taskList.addTask(102, "Develop Login Module", "In Progress");
        taskList.addTask(103, "Test Application", "Pending");

        System.out.println("===== All Tasks =====");
        taskList.traverseTasks();

        System.out.println("\n===== Search Task =====");
        taskList.searchTask(102);

        System.out.println("\n===== Delete Task =====");
        taskList.deleteTask(102);

        System.out.println("\n===== Tasks After Deletion =====");
        taskList.traverseTasks();
    }
}