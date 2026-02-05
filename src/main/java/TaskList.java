import java.util.ArrayList;

class TaskList {
    /** Initiliase tasks variable
     * tasks is a ArrayList consisting of only Task-typed elements **/
    private final ArrayList<Task> tasks;

    /** Constructor for a task list **/
    public TaskList() {
        tasks = new ArrayList<Task>();
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
    public Task getTask(int i) {
        return tasks.get(i);
    }

}
