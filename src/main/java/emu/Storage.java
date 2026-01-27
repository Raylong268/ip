package emu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;
    private String link;

    public Storage(String link) {
        this.file = new File(link);
        this.link = link;
        try {
            file.getParentFile().mkdir();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public TaskList initialiseList() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            // create a Scanner using the File as the source
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String text = scanner.nextLine();
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
            throw new DukeException("UWA!!! I can't seem to find your past tasks!");
        }
    }

    public void resetList(TaskList tasks) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(link);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fileWriter.write(task.record() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}