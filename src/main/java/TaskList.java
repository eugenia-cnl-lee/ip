/** TaskList owns the collection **/
/** TaskList THROWS **/

import java.util.ArrayList;

public class TaskList {

    /** Define tasks variable
     * tasks is a ArrayList consisting of only Task-typed elements **/
    private final ArrayList<Task> tasks;

    /** Constructor for a task list **/
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /** Method for appending a task object to task list **/
    public void addTask(Task task) {
        tasks.add(task);
    }

    /** Getters for size of task list **/
    public int getSize() {
        return tasks.size();
    }

    /** Getters for a task element of the given index **/
    public Task getTask(int index) throws AthenaException {
        if (index < 0 || index >= tasks.size()) {
            throw new AthenaException("The task number is invalid, you fool. Is your head just there for decoration?");
        }
        return tasks.get(index);
    }

    /** Non-throwing accessor for listing **/
    public Task getTaskUnsafe(int index) {
        return tasks.get(index);
    }
}
