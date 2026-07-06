public class TaskLinkedList {

    private Task head;

    // Add Task
    public void addTask(int taskId, String taskName, String status) {

        Task newTask = new Task(taskId, taskName, status);

        if (head == null) {
            head = newTask;
            return;
        }

        Task temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newTask;
    }

    // Traverse Tasks
    public void traverseTasks() {

        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        Task temp = head;

        while (temp != null) {
            System.out.println("----------------------");
            System.out.println("Task ID   : " + temp.taskId);
            System.out.println("Task Name : " + temp.taskName);
            System.out.println("Status    : " + temp.status);

            temp = temp.next;
        }
    }

    // Search Task
    public void searchTask(int id) {

        Task temp = head;

        while (temp != null) {

            if (temp.taskId == id) {
                System.out.println("Task Found");
                System.out.println("Task ID   : " + temp.taskId);
                System.out.println("Task Name : " + temp.taskName);
                System.out.println("Status    : " + temp.status);
                return;
            }

            temp = temp.next;
        }

        System.out.println("Task not found.");
    }

    // Delete Task
    public void deleteTask(int id) {

        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.taskId == id) {
            head = head.next;
            System.out.println("Task deleted successfully.");
            return;
        }

        Task current = head;
        Task previous = null;

        while (current != null && current.taskId != id) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Task not found.");
            return;
        }

        previous.next = current.next;

        System.out.println("Task deleted successfully.");
    }
}