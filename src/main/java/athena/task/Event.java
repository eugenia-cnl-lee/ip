package athena.task;

/**
 * Represents an event task.
 * <p>
 * An Event is a specific type of {@link Task} that occurs within a
 * defined time interval, consisting of a start time and an end time.
 * </p>
 */
public class Event extends Task {

    /** The starting time of the event. */
    protected String from;

    /** The ending time of the event. */
    protected String to;

    /**
     * Constructs an Event task with a description, start time, and end time.
     *
     * @param description the description of the event
     * @param from the starting time of the event
     * @param to the ending time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     * <p>
     * The format includes the task type, completion status,
     * description, and the event time range.
     * </p>
     *
     * @return a formatted string representing the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the event task into a string format suitable for file storage.
     * <p>
     * The stored format includes the task type identifier, completion status,
     * description, start time, and end time.
     * </p>
     *
     * @return a string representation used when saving the task to a file
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}