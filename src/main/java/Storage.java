import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File f;

    public Storage() {
        this.f = new File("./data/tasks.txt");
        try {
            f.getParentFile().mkdir();
            f.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public TaskList initialiseList() throws DukeException {
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
            throw new DukeException("UWA!!! I can't seem to find your past tasks!");
        }
    }

    public void resetList(TaskList tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter("./data/tasks.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fw.write(task.record() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
