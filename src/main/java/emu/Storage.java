package emu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores and loads the tasks from the hard drive
 * to allow tasks to persist between sessions
 */
public class Storage {
    private File f;
    private String link;

    /**
     * Initialises a Storage using the given file path.
     * If the file or its parent directory does not exist,
     * they will be created.
     *
     * @param link File path used for storage.
     */
    public Storage(String link) {
        this.f = new File(link); // File used for storage.
        this.link = link;
        try {
            f.getParentFile().mkdir();
            f.createNewFile();
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
            ArrayList<Task> temp = new ArrayList<>();
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String text = s.nextLine();
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
                temp.add(task);
            }
            return new TaskList(temp);
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
        try {
            FileWriter fw = new FileWriter(link);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fw.write(task.record() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new EmuException("I couldn't record the tasks!");
        }
    }
}
