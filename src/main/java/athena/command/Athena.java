package athena.command;
/** athena.command.Athena controls the command flow **/

import java.util.Scanner;

import athena.task.Deadline;
import athena.task.Event;
import athena.task.Task;
import athena.task.TaskList;
import athena.task.Todo;
import athena.ui.Ui;
import athena.data.Storage;
import athena.parser.Parser;

public class Athena {

    private final Ui ui;
    private final Scanner scanner;
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /** Constructs an athena.command.Athena chatbot instance
    Initialise the UI and input reader **/
    public Athena() {
        this.ui = new Ui();
        this.scanner = new Scanner(System.in);
        this.storage = new Storage("data/athena.txt", "data");
        this.parser = new Parser();

        TaskList loadedTasks;
        try {
            loadedTasks = storage.load();
        } catch (AthenaException e) {
            ui.showError("Unable to load saved tasks. Starting with an empty task list instead.");
            loadedTasks = new TaskList();
        }

        this.tasks = loadedTasks;
    }

    private void processCommand(String inputLine) throws AthenaException {
        String[] inputParts = parser.splitInput(inputLine);
        String command = parser.parseCommandWord(inputLine);

        switch (command) {
        case "bye":
            ui.showExit();
            return;

        case "list":
            ui.showTaskList(tasks);
            return;

        case "mark": {
            int idx = parser.parseIndex(inputParts);
            Task t = tasks.getTask(idx);
            t.markAsDone();
            storage.save(tasks);
            ui.showTaskMarkedAsDone(t);
            return;
        }

        case "unmark": {
            int idx = parser.parseIndex(inputParts);
            Task t = tasks.getTask(idx);
            t.markAsUndone();
            storage.save(tasks);
            ui.showTaskMarkedAsUndone(t);
            return;
        }

        case "find": {
            String keyword = parser.parseFindKeyword(inputParts);
            TaskList matches = tasks.findTasks(keyword);
            ui.showTaskList(matches);
            return;
        }

        case "delete": {
            int idx = parser.parseIndex(inputParts);
            Task t = tasks.getTask(idx);
            tasks.deleteTask(t);
            storage.save(tasks);
            ui.showTaskDeleted(t, tasks);
            return;
        }

        case "todo": {
            String description = parser.parseTodoDescription(inputParts);
            Task todo = new Todo(description);
            tasks.addTask(todo);
            storage.save(tasks);
            ui.showTaskAdded(todo, tasks);
            return;
        }

        case "deadline": {
            String[] deadlineParts = parser.parseDeadlineParts(inputParts);
            Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
            tasks.addTask(deadline);
            storage.save(tasks);
            ui.showTaskAdded(deadline, tasks);
            return;
        }

        case "event": {
            String[] eventParts = parser.parseEventParts(inputParts);
            Task event = new Event(eventParts[0], eventParts[1], eventParts[2]);
            tasks.addTask(event);
            storage.save(tasks);
            ui.showTaskAdded(event, tasks);
            return;
        }

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
