public class Ui {

    /** Horizontal line used for formatting output **/
    private static final String LINE = "========================================";

    /** Prints the welcome message when the user starts the program **/
    public void showGreeting() {
        System.out.println("Hello wisdom-seeker! I am Athena, your personal assistant chatbot.");
        System.out.println("I am named after the Greek goddess of wisdom, as I possess all the worldly knowledge.");
        System.out.println("Thus, I am able to assist you in all ways.");
        System.out.println("What can I do for you, my curious child?");
        System.out.println(LINE);
    }

    /** Prints the user's input back to the console **/
    public void showEcho(String input) {
        System.out.println(input);
        System.out.println(LINE);
    }

    /** Prints a task element in task list back to the console **/
    public void showEcho(Task task) {
        System.out.println(task);
    }

    /** Prints all the task elements in the task list **/
    public void showTaskList(TaskList tasks) {
        for (int i = 1; i = tasks.getSize(); i++) {
            int index = i - 1;
            Task task = tasks.getTask(index);
            System.out.println(i + ". " + Ui.showEcho(task));
        }
        System.out.println(LINE);
    }

    /** Prints the goodbye message when the user exits the program **/
    public void showExit() {
        System.out.println(LINE);
        System.out.println("You no longer require my assistance? Then I shall take my leave now.");
        System.out.println("I hope my teachings of today reside with you forever.");
        System.out.println("May your thirst for knowledge be as fierce and as eternal as Greek Fire.");
    }
}
