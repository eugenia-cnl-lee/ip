package athena.command;
/** athena.command.Athena controls the command flow **/
/** athena.command.Athena THROWS **/

import java.util.Scanner;

import athena.task.Deadline;
import athena.task.Event;
import athena.task.Task;
import athena.task.TaskList;
import athena.task.Todo;
import athena.ui.Ui;

public class Athena {

    private final Ui ui;
    private final Scanner scanner;
    private final TaskList tasks;

    /** Constructs an athena.command.Athena chatbot instance
    Initialise the UI and input reader **/
    public Athena() {
        this.ui = new Ui();
        this.scanner = new Scanner(System.in);
        this.tasks = new TaskList();
    }

    /** Error Handling:
    parses index from user input **/
    private static int parseIndex(String[] inputParts) throws AthenaException {
        if (inputParts.length != 2 || inputParts[1].trim().isEmpty()) {
            throw new AthenaException("Provide a task number, you fool.");
        }
        String s = inputParts[1].trim();
        try {
            return Integer.parseInt(s) - 1;
        } catch (NumberFormatException e) {
            throw new AthenaException("athena.task.Task number must be an integer, you fool.");
        }
    }

    private void processCommand(String inputLine) throws AthenaException {
        String[] inputParts = inputLine.split(" ", 2);
        String command = inputParts[0].toLowerCase();

        switch (command) {
        case "bye":
            ui.showExit();
            System.exit(0); // or return via a boolean; see note below

        case "list":
            ui.showTaskList(tasks);
            return;

        case "mark": {
            int idx = parseIndex(inputParts);
            Task t = tasks.getTask(idx);
            t.markAsDone();
            ui.showTaskMarkedAsDone(t);
            return;
        }

        case "unmark": {
            int idx = parseIndex(inputParts);
            Task t = tasks.getTask(idx);
            t.markAsUndone();
            ui.showTaskMarkedAsUndone(t);
            return;
        }

        case "todo":
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new AthenaException("Provide a task description, you fool.");
            }
            Task todo = new Todo(inputParts[1].trim());
            tasks.addTask(todo);
            ui.showTaskAdded(todo, tasks);
            return;

        case "deadline":
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new AthenaException("Provide a task description and deadline, you fool.");
            }
            String[] deadlineParts = inputParts[1].split("by ", 2);
            if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                throw new AthenaException("Use the format: deadline <description> by <time>, you fool.");
            }
            Task deadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            tasks.addTask(deadline);
            ui.showTaskAdded(deadline, tasks);
            return;

        case "event":
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new AthenaException("Provide a task description, start, and end time, you fool.");
            }
            String[] eventParts = inputParts[1].split("from ", 2);
            if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
                throw new AthenaException("Use the format: event <description> from <start> to <end>, you fool.");
            }
            String[] timeParts = eventParts[1].split("to ", 2);
            if (timeParts.length < 2 || timeParts[1].trim().isEmpty()) {
                throw new AthenaException("Use the format: event <description> from <start> to <end>, you fool.");
            }
            Task event = new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
            tasks.addTask(event);
            ui.showTaskAdded(event, tasks);
            return;

        default:
            throw new AthenaException("You speak of nonsense, you fool.");
        }
    }

    /** run() CATCHES **/
    public void run() {
        ui.showGreeting();
        while (true) {
            String inputLine = scanner.nextLine().trim();
            if (inputLine.isEmpty()) {
                ui.emptyCommand();
                continue;
            }
            try {
                processCommand(inputLine);
            } catch (AthenaException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /** Program entry point **/
    public static void main(String[] args) {
        new Athena().run();
    }
}
