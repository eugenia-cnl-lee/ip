package athena.task;

/**
 * Represents a generic task in the task list.
 * <p>
 * A Task contains a description and a completion status.
 * Specific task types such as {@code Todo}, {@code Deadline}, and {@code Event}
 * extend this class and may include additional fields or behavior.
 * </p>
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * <p>
     * Newly created tasks are marked as not done by default.
     * </p>
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is completed.
     *
     * @return "X" if the task is completed, otherwise a space character
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * <p>
     * The format shows the completion status followed by the task description.
     * </p>
     *
     * @return a formatted string representing the task
     */
    @Override
    public String toString() {
        return String.format("[%1$s] %2$s", getStatusIcon(), this.description);
    }

    /**
     * Converts the task into a string format suitable for saving to a file.
     * <p>
     * Subclasses should override this method to provide their own
     * storage format.
     * </p>
     *
     * @return a string representation used for file storage
     */
    public String toFileString() {
        return "";
    }
}
