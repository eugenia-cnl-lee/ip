package athena.task;

/**
 * Represents a todo task.
 * <p>
 * A Todo is a simple type of {@link Task} that only contains a description
 * and does not have any associated time information.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     * <p>
     * The format includes the task type identifier followed by
     * the task status and description.
     * </p>
     *
     * @return a formatted string representing the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo task into a string format suitable for file storage.
     * <p>
     * The stored format includes the task type identifier,
     * completion status, and task description.
     * </p>
     *
     * @return a string representation used when saving the task to a file
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}