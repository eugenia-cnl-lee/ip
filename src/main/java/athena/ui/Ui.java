package athena.ui;

import athena.task.Task;
import athena.task.TaskList;

/** athena.ui.Ui prints output **/

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
        System.out.println("Hello wisdom-seeker! I am athena.command.Athena, your personal assistant chatbot.");
        System.out.println("I am named after the Greek goddess of wisdom, as I possess all the worldly knowledge.");
        System.out.println("Thus, I am able to assist you in all ways.");
        System.out.println("What can I do for you, my curious child?");
        printLine();
    }

    /** Prints the goodbye message when the user exits the program **/
    public void showExit() {
        printLine();
        System.out.println("You no longer require my assistance? Then I shall take my leave now.");
        System.out.println("I hope my teachings of today reside with you forever.");
        System.out.println("May your thirst for knowledge be as fierce and as eternal as Greek Fire.");
        printLine();
    }

    /** Prints the added task back to console **/
    public void showTaskAdded(Task task, TaskList tasks) {
        printLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println(INDENT + task.toString());
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        printLine();
    }

    /** Prints the deleted task to console **/
    public void showTaskDeleted(Task task, TaskList tasks) {
        printLine();
        System.out.println("I have removed this task from your to-conquest list:");
        System.out.println(INDENT + task.toString());
        System.out.println("You disappoint me. Now you have " + tasks.getSize() + " in the list.");
        printLine();
    }

    /** Prints all the task elements in the task list **/
    public void showTaskList(TaskList tasks) {
        printLine();
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTaskUnsafe(i));
        }
        printLine();
    }

    /** Prints the task which has been marked as done **/
    public void showTaskMarkedAsDone(Task task) {
        printLine();
        System.out.println("Well done scholar. I've marked this quest as done:");
        System.out.println(INDENT + task.toString());
        printLine();
    }

    /** Prints the task which has been unmarked hence not done **/
    public void showTaskMarkedAsUndone(Task task) {
        printLine();
        System.out.println("You incompetent fool. This quest has now been marked as undone:");
        System.out.println(INDENT + task.toString());
        printLine();
    }

    /** Error Handling: when user does not input anything **/
    public void emptyCommand() {
        printLine();
        System.out.println("Are you mute? Speak up.");
        printLine();
    }

    /** Error Handling: general error message is thrown **/
    public void showError(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }
}
