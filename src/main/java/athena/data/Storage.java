package athena.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import athena.command.AthenaException;
import athena.task.Deadline;
import athena.task.Event;
import athena.task.Todo;
import athena.task.Task;
import athena.task.TaskList;

public class Storage {
    private final String filePath;
    private final String dirPath;

    public Storage(String filePath, String dirPath) {
        this.filePath = filePath;
        this.dirPath = dirPath;
    }

    public void loadDataFile() throws IOException {
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void save(TaskList taskList) throws AthenaException {
        try {
            loadDataFile();

            FileWriter fw = new FileWriter(filePath);

            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTaskUnsafe(i);
                fw.write(task.toFileString() + System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            throw new AthenaException("Unable to save data to file.");
        }
    }

    public TaskList load() throws AthenaException {
        TaskList taskList = new TaskList();

        try {
            loadDataFile();

            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.isBlank()) {
                    Task task = parseTask(line);
                    taskList.addTask(task);
                }
            }

            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new AthenaException("Data file not found.");
        } catch (IOException e) {
            throw new AthenaException("Unable to load data from file.");
        }
    }

    private Task parseTask(String line) throws AthenaException {
        String[] parts = line.split(" \\| ");

        String type = parts[0];
        String isDone = parts[1];
        String description = parts[2];

        Task task;

        if (type.equals("T")) {
            task = new Todo(description);
        } else if (type.equals("D")) {
            String by = parts[3];
            task = new Deadline(description, by);
        } else if (type.equals("E")) {
            String from = parts[3];
            String to = parts[4];
            task = new Event(description, from, to);
        } else {
            throw new AthenaException("Corrupted task data file.");
        }

        if (isDone.equals("1")) {
            task.markAsDone();
        }

        return task;
    }
}