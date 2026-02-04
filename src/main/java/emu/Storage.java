package emu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles persistence of tasks by saving them to
 * and loading them from a file on hard disk
 */
public class Storage {
    private static final String DIVIDER = " \\| ";
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private static final String COMPLETED_MARKER = "X";

    private final File storageFile;

    /**
     * Creates a Storage object that uses the given {@code filePath}
     * to persist task data. If the file or its parent directories
     * do not exist, they will be created
     *
     * @param filePath Path to the storage file
     * @throws EmuException If the storage file cannot be created
     */
    public Storage(String filePath) throws EmuException {
        this.storageFile = new File(filePath);

        try {
            File parentDirectory = storageFile.getParentFile();
            if (parentDirectory != null) {
                parentDirectory.mkdir();
            }
            storageFile.createNewFile();
        } catch (IOException e) {
            throw new EmuException("I couldn't access the storage file!");
        }
    }

    /**
     * Loads tasks from the storage file and reconstructs
     * them into a TaskList
     *
     * @return A TaskList containing all stored tasks
     * @throws EmuException If the storage file cannot be read
     */
    public TaskList initialiseList() throws EmuException {
        ArrayList<Task> tasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(storageFile)) {
            while (scanner.hasNextLine()) {
                Task task = parseTask(scanner.nextLine());
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new EmuException("UWA!!! I can't seem to find your past tasks!");
        }

        return new TaskList(tasks);
    }

    /**
     * Writes the current state of the given {@code tasks}
     * to the storage file, overwriting any existing data
     *
     * @param tasks The up-to-date task list to be stored
     * @throws EmuException If the tasks cannot be written to the file
     */
    public void resetList(TaskList tasks) throws EmuException {
        try (FileWriter writer = new FileWriter(storageFile)) {
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.getTask(i).toStorageString() + "\n");
            }
        } catch (IOException e) {
            throw new EmuException("I couldn't record the tasks!");
        }
    }

    /**
     * Parses a single line from the storage file and converts it
     * into the corresponding Task object
     *
     * @param line A single line from the storage file
     * @return The reconstructed Task, or null if the line is invalid
     */
    private Task parseTask(String line) {
        String[] parts = line.split(DIVIDER);
        Task task;

        if (TODO_TYPE.equals(parts[0])) {
            task = new ToDo(parts[2]);
        } else if (DEADLINE_TYPE.equals(parts[0])) {
            task = new Deadline(parts[2], parts[3]);
        } else if (EVENT_TYPE.equals(parts[0])) {
            task = new Event(parts[2], parts[3], parts[4]);
        } else {
            return null;
        }

        if (COMPLETED_MARKER.equals(parts[1])) {
            task.markComplete();
        }

        return task;
    }
}