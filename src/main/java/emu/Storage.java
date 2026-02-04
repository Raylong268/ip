package emu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores and loads the tasks from the hard drive
 * to allow tasks to persist between sessions
 */
public class Storage {
    private File file;
    private String link;

    /**
     * Initialises a Storage using the given file path.
     * If the file or its parent directory does not exist,
     * they will be created.
     *
     * @param link File path used for storage.
     */
    public Storage(String link) {
        assert link != null : "link should not be null";
        assert !link.isEmpty() : "link should not be empty";

        this.file = new File(link); // File used for storage.
        this.link = link;
        try {
            file.getParentFile().mkdir();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Recreates the TaskList from the text-file given in constructor
     *
     * @return The TaskList representation of the text in the text-file
     * @throws EmuException If the scanner is unable to read the file
     */
    public TaskList initialiseList() throws EmuException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            // create a Scanner using the File as the source
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String text = scanner.nextLine();
                assert text != null : "text should not be null";
                assert !text.isEmpty() : "text should not be empty";

                Task task;
                String[] parts = text.split(" \\| ");

                if (parts[0].equals("T")) {
                    task = new ToDo(parts[2]);
                } else if (parts[0].equals("D")) {
                    task = new Deadline(parts[2], parts[3]);
                } else if (parts[0].equals("E")) {
                    task = new Event(parts[2], parts[3], parts[4]);
                } else {
                    continue;
                }

                if (parts[1].equals("X")) {
                    task.markDone();
                }

                tasks.add(task);
            }
            return new TaskList(tasks);
        } catch (FileNotFoundException e) {
            throw new EmuException("UWA!!! I can't seem to find your past tasks!");
        }
    }

    /**
     * Records the current TaskList back into the text-file
     * given in constructor, to store the tasks for future sessions
     *
     * @param tasks The up-to-date list of tasks
     * @throws EmuException If the FileWriter is unable to record the tasks into the file
     */
    public void resetList(TaskList tasks) throws EmuException {
        assert tasks != null : "tasks cannot be null";

        try {
            FileWriter fileWriter = new FileWriter(link);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                assert task != null : "task should not be null";
                fileWriter.write(task.record() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new EmuException("I couldn't record the tasks!");
        }
    }
}