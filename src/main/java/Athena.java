/** Athena controls the command flow **/

import java.util.Scanner;

public class Athena {

    private final Ui ui;
    private final Scanner scanner;
    private final TaskList tasks;

    /** Constructs an Athena chatbot instance
    Initialise the UI and input reader **/
    public Athena() {
        this.ui = new Ui();
        this.scanner = new Scanner(System.in);
        this.tasks = new TaskList();
    }

    /** Runs the main chatbot loop:
    Reads user input, echoes it, and exits when user enters "bye" **/
    public void run() {
        ui.showGreeting();

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                ui.showExit();
                break;

            } else if (input.equals("list")) {
                ui.showTaskList(tasks);

            } else {
                Task task = new Task(input);
                tasks.addTask(task);
                ui.showTaskAdded(task, tasks.getSize());
            }
        }
    }

    /** Program entry point **/
    public static void main(String[] args) {
        new Athena().run();
    }
}
