/** Ui prints output **/

public class Ui {

    /** Horizontal line used for formatting output **/
    private static final String LINE = "========================================";

    /** Indent used for formatting output **/
    private static final String INDENT = "  ";

    /** Prints out formatting line **/
    private void printLine() {
        System.out.println(LINE);
    }

    /** Prints the welcome message when the user starts the program **/
    public void showGreeting() {
        System.out.println("Hello wisdom-seeker! I am Athena, your personal assistant chatbot.");
        System.out.println("I am named after the Greek goddess of wisdom, as I possess all the worldly knowledge.");
        System.out.println("Thus, I am able to assist you in all ways.");
        System.out.println("What can I do for you, my curious child?");
        printLine();
    }

    /** Prints the goodbye message when the user exits the program **/
    public void showExit() {
        System.out.println(LINE);
        System.out.println("You no longer require my assistance? Then I shall take my leave now.");
        System.out.println("I hope my teachings of today reside with you forever.");
        System.out.println("May your thirst for knowledge be as fierce and as eternal as Greek Fire.");
        printLine();
    }

    /** Prints the added task back to console **/
    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(INDENT + task.toString());
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        printLine();
    }

    /** Prints all the task elements in the task list **/
    public void showTaskList(TaskList tasks) {
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        printLine();
    }

    /** Prints the task which has been marked as done **/
    public void showTaskMarkedAsDone(Task task) {
        System.out.println("Well done scholar. I've marked this quest as done:");
        System.out.println(task.toString());
        printLine();
    }

    /** Prints the task which has been unmarked hence not done **/
    public void showTaskMarkedAsUndone(Task task) {
        System.out.println("You incompetent fool. This quest has now been marked as undone:");
        System.out.println(task.toString());
        printLine();
    }
}
