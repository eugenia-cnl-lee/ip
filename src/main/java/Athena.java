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
    private static int extractIndex(String[] inputParts) {
        if (inputParts.length != 2) {
            return -1;
        }
        return Integer.parseInt(inputParts[1]);
    }

    /** Runs the main chatbot loop:
    Reads user input line, echoes it, and exits when user enters "bye" **/
    public void run() {
        ui.showGreeting();

        while (true) {
            String inputLine = scanner.nextLine().trim();
            String[] inputParts = inputLine.split(" ", 2);
            String command = inputParts[0].toLowerCase();

            switch (command) {
            case "bye":
                ui.showExit();
                return;

            case "list":
                ui.showTaskList(tasks);
                break;

            case "mark":
                int markedIndex = extractIndex(inputParts);
                Task markedTask = tasks.getTask(markedIndex);
                markedTask.markAsDone();
                ui.showTaskMarkedAsDone(markedTask);
                break;

            case "unmark":
                int unmarkedIndex = extractIndex(inputParts);
                Task unmarkedTask = tasks.getTask(unmarkedIndex);
                unmarkedTask.markAsUndone();
                ui.showTaskMarkedAsUndone(unmarkedTask);
                break;

            case "todo":
                Task todo = new Todo(inputParts[1]);
                tasks.addTask(todo);
                ui.showTaskAdded(todo, tasks);
                break;

            case "deadline":
                String[] deadlineParts = inputParts[1].split("/by ", 2);
                Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                tasks.addTask(deadline);
                ui.showTaskAdded(deadline, tasks);
                break;

            case "event" :
                String[] eventParts = inputParts[1].split("/from ", 2);
                String[] timeParts = eventParts[1].split("/to ", 2);
                Task event = new Event(eventParts[0], timeParts[0], timeParts[1]);
                tasks.addTask(event);
                ui.showTaskAdded(event, tasks);
                break;

            default:
                Task task = new Task(inputLine);
                tasks.addTask(task);
                ui.showTaskAdded(task, tasks);
                break;
            }
        }
    }

    /** Program entry point **/
    public static void main(String[] args) {
        new Athena().run();
    }
}
