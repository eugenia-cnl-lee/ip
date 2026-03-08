package athena.task;

/**
 * Represents a deadline task.
 * <p>
 * A Deadline is a specific type of {@link Task} that must be completed
 * before a specified time or date.
 * </p>
 */
public class Deadline extends Task {

    /** The deadline time associated with the task. */
     private String by;

    /**
     * Constructs a Deadline task with a description and deadline time.
     *
     * @param description the description of the task
     * @param by the deadline time for completing the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     * <p>
     * The format includes the task type, completion status,
     * description, and deadline time.
     * </p>
     *
     * @return a formatted string representing the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts the deadline task into a string format suitable for file storage.
     * <p>
     * The stored format includes the task type identifier,
     * completion status, description, and deadline time.
     * </p>
     *
     * @return a string representation used when saving the task to a file
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
