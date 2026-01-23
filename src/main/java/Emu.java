import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Emu {
    private TaskList tasks;
    private Storage storage;

    public Emu() {
        /*
        initialises a new ArrayList storage
         */
        try {
            this.storage = new Storage();
            this.tasks = storage.initialiseList();
        } catch (FileNotFoundException e) {
            System.out.println("UWA!!! I can't seem to find your past tasks!");
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    public String respond(String command, String other) throws DukeException, IOException {
        if (command.equals("bye")) {
            storage.resetList(tasks);
            return " Bye. Hope to see you again soon!\n";
        } else if (command.equals("list")) {
            return tasks.list();
        } else if (command.equals("mark")) {
            try {
                int number = Integer.parseInt(other);
                return tasks.mark(number);
            } catch (NumberFormatException e) {
                throw new DukeException("That's not a valid number silly!");
            }
        } else if (command.equals("unmark")) {
            try {
                int number = Integer.parseInt(other);
                return tasks.unmark(number);
            } catch (NumberFormatException e) {
                throw new DukeException("That's not a number silly!");
            }
        } else if (command.equals("todo")) {
             /*
            makes a ToD0 task, or throws dukeException if
            given no valid input
             */
            if (other.isEmpty()) {
                throw new DukeException("You can't make a todo " +
                        "without a description silly!");
            }
            return tasks.todo(other);
        } else if (command.equals("deadline")) {
             /*
            makes a deadline task, or throws dukeException if
            given no valid input
             */
            int slash = other.indexOf("/by");

            if (slash == -1) {
                throw new DukeException(
                        "You forgot to put /by in your deadline task!");
            }

            String desc = other.substring(0, slash).trim();
            String by = other.substring(slash + 3).trim();

            if (desc.isEmpty() || by.isEmpty()) {
                throw new DukeException("You can't make a deadline " +
                        "without a description and a date silly!");
            }
            return tasks.deadline(other, other);
        } else if (command.equals("event")) {
             /*
            makes an event task, or throws dukeException if
            given no valid input
             */
            int slashfrom = other.indexOf("/from");
            int slashto = other.indexOf("/to");

            if (slashfrom == -1 || slashto == -1 || slashto < slashfrom) {
                throw new DukeException(
                        "You put in the wrong format! " +
                                "Use event (desc) /from (from) /to (to) instead!");
            }

            String desc = other.substring(0, slashfrom).trim();
            String from = other.substring(slashfrom + 5, slashto).trim();
            String to = other.substring(slashto + 3).trim();

            if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new DukeException("You can't make an event " +
                        "without a description, from date and to date silly!");
            }

            return tasks.event(other, other, other);
        } else if (command.equals("delete")) {
             /*
            deletes a task, or throws dukeException if
            given an invalid task
             */
            try {
                int number = Integer.parseInt(other);
                return tasks.delete(number);
            } catch (NumberFormatException e) {
                throw new DukeException("That's not a number silly!");
            }
        } else {
             /*
            throws a dukeException for invalid command
             */
            throw new DukeException("I don't get what that means!!");
        }
    }

    public static void main(String[] args) {
        Emu emu = new Emu();
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;

        UI ui = new UI();

        while (!stop) {
             /*
            continues scanning, until "bye" input sets
            stop to true
             */
            try {
                String input = ui.scan();
                Parser parts = new Parser(input);
                String response = emu.respond(parts.getCommand(), parts.getOther());
                ui.respond(response);
            } catch (IOException e) {
                ui.respond(" UWA!!! I couldn't record that task!" + "\n");
            } catch (DukeException e) {
                /*
                catches any dukeExceptions from emu.respond
                 */
                ui.respond(e.getMessage());
            }
        }
    }
}
