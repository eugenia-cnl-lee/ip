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

    /** Helper function:
    extracts the index from user input **/
    private static int extractIndex(String line) {
        String[] parts = line.split(" ");
        if (parts.length < 2) {
            return -1;
        }
        return Integer.parseInt(parts[1]) - 1;
    }

    /** Runs the main chatbot loop:
    Reads user input line, echoes it, and exits when user enters "bye" **/
    public void run() {
        ui.showGreeting();

        while (true) {
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("bye")) {
                ui.showExit();
                break;

            } else if (line.equalsIgnoreCase("list")) {
                ui.showTaskList(tasks);

            } else if (line.toLowerCase().startsWith("mark")) {
                int index = extractIndex(line);
                Task task = tasks.getTask(index);
                task.markAsDone();
                ui.showTaskMarkedAsDone(task);
                
            } else if (line.toLowerCase().startsWith("unmark")) {
                int index = extractIndex(line);
                Task task = tasks.getTask(index);
                task.markAsUndone();
                ui.showTaskMarkedAsUndone(task);

            } else {
                Task task = new Task(line);
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
