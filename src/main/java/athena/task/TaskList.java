package athena.task;

import java.util.ArrayList;
import athena.command.AthenaException;

/**
 * TaskList manages the collection of tasks in the chatbot.
 * <p>
 * It stores tasks in an {@link ArrayList} and provides operations
 * for adding tasks, deleting tasks, retrieving tasks, and searching
 * for tasks based on a keyword.
 * </p>
 */
public class TaskList {

    /**
     * Internal list that stores all tasks currently managed by the application.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * <p>
     * Initializes the internal task storage as an empty {@link ArrayList}.
     * </p>
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param task the task to be removed from the list
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Returns the total number of tasks currently stored in the list.
     *
     * @return the number of tasks in the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves a task at a specific index from the task list.
     *
     * @param index the position of the task in the list (zero-based)
     * @return the task located at the specified index
     * @throws AthenaException if the provided index is invalid
     */
    public Task getTask(int index) throws AthenaException {
        if (index < 0 || index >= tasks.size()) {
            throw new AthenaException("The task number is invalid, you fool. Is your head just there for decoration?");
        }
        return tasks.get(index);
    }

    /**
     * Retrieves a task at a specific index without performing validation checks.
     * <p>
     * This method assumes the caller already ensures that the index is valid.
     * It is typically used in internal operations such as listing tasks.
     * </p>
     *
     * @param index the position of the task in the list (zero-based)
     * @return the task at the specified index
     */
    public Task getTaskUnsafe(int index) {
        return tasks.get(index);
    }

    /**
     * Searches for tasks that contain a given keyword in their description.
     * <p>
     * The search is case-insensitive and matches tasks whose string
     * representation contains the keyword.
     * </p>
     *
     * @param keyword the keyword used to search for matching tasks
     * @return a new {@code TaskList} containing all matching tasks
     */
    public TaskList findTasks(String keyword) {
        TaskList matches = new TaskList();
        for (Task task : tasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matches.addTask(task);
            }
        }
        return matches;
    }
}
