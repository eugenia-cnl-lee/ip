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
        String s = inputParts[1].trim();
        if (s.isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(inputParts[1]) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /** Runs the main chatbot loop:
    Reads user input line, echoes it, and exits when user enters "bye" **/
    public void run() {
        ui.showGreeting();

        while (true) {
            String inputLine = scanner.nextLine().trim();

            if (inputLine.isEmpty()) {
                ui.emptyCommand();
                continue;
            }

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
                if (markedIndex < 0 || markedIndex >= tasks.getSize()) {
                    ui.invalidTaskNumber();
                    break;
                }
                Task markedTask = tasks.getTask(markedIndex);
                markedTask.markAsDone();
                ui.showTaskMarkedAsDone(markedTask);
                break;

            case "unmark":
                int unmarkedIndex = extractIndex(inputParts);
                if (unmarkedIndex < 0 || unmarkedIndex >= tasks.getSize()) {
                    ui.invalidTaskNumber();
                    break;
                }
                Task unmarkedTask = tasks.getTask(unmarkedIndex);
                unmarkedTask.markAsUndone();
                ui.showTaskMarkedAsUndone(unmarkedTask);
                break;

            case "todo":
                if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                    ui.askTodoDescription();
                    break;
                }
                Task todo = new Todo(inputParts[1]);
                tasks.addTask(todo);
                ui.showTaskAdded(todo, tasks);
                break;

            case "deadline":
                if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                    ui.askDeadlineDescription();
                    break;
                }
                String[] deadlineParts = inputParts[1].split("by ", 2);
                if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                    ui.askDeadlineTime();
                    break;
                }
                Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                tasks.addTask(deadline);
                ui.showTaskAdded(deadline, tasks);
                break;

            case "event":
                if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                    ui.askEventDescription();
                    break;
                }
                String[] eventParts = inputParts[1].split("from ", 2);
                if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
                    ui.askEventTime();
                    break;
                }
                String[] timeParts = eventParts[1].split("to ", 2);
                if (timeParts.length < 2 || timeParts[1].trim().isEmpty()) {
                    ui.askEventTime();
                    break;
                }
                Task event = new Event(eventParts[0], timeParts[0], timeParts[1]);
                tasks.addTask(event);
                ui.showTaskAdded(event, tasks);
                break;

            default:
                ui.unknownCommand();
                break;
            }
        }
    }

    /** Program entry point **/
    public static void main(String[] args) {
        new Athena().run();
    }
}
