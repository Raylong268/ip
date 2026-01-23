import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Emu {
    private ArrayList<Task> storage;
    private Storage file;
    String line = "____________________________________________________________\n";

    public Emu() {
        /*
        initialises a new ArrayList storage
         */
        try {
            file = new Storage();
            storage = file.initialiseList();
        } catch (FileNotFoundException e) {
            System.out.println("UWA!!! I can't seem to find your past tasks!");
            storage = new ArrayList<>();
        }
    }

    public boolean respond(String fullResponse) throws DukeException, IOException {
        /*
        splits the response into 2 parts, the first word
        command and the other part
         */
        int firstSpace = fullResponse.indexOf(' ');
        String command = (firstSpace == -1) ? fullResponse
                : fullResponse.substring(0, firstSpace);
        String other = (firstSpace == -1) ? ""
                : fullResponse.substring(firstSpace + 1);
        if (command.equals("bye")) {
            /*
            returns true when bye is inputted
            to stop scanner loop
             */
            file.resetList(storage);
            System.out.println(
                    line +
                    " Bye. Hope to see you again soon!\n" +
                    line);
            return true;
        } else if (command.equals("list")) {
            /*
            lists all Tasks in the ArrayList
             */
            int listing = 1;
            String temp = "Here are the tasks in your list:\n";
            while (listing <= storage.size()) {
                Task task = storage.get(listing - 1);
                temp += listing +
                        "." + task.toString() +
                        "\n";
                listing++;
            }
            System.out.println(line + temp + line);
        } else if (command.equals("mark")) {
            /*
            marks a task if valid input in other,
            otherwise DukeException
             */
            try {
                int tasknumber = Integer.parseInt(other);
                if (tasknumber <= storage.size()) {
                    Task task = storage.get(tasknumber - 1);
                    task.markDone();
                    System.out.println(
                        line +
                        "  Nice! I've marked this task as done:\n" +
                        "    " + task.toString() +
                        "\n" +
                        line);
                } else {
                    throw new DukeException("That's not a valid task silly!");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("That's not a valid number silly!");
            }
        } else if (command.equals("unmark")) {
             /*
            unmarks a task if valid input in other,
            otherwise DukeException
             */
            try {
                int tasknumber = Integer.parseInt(other);
                if (tasknumber <= storage.size()) {
                    Task task = storage.get(tasknumber - 1);
                    task.markUndone();
                    System.out.println(
                            line +
                            "  OK, I've marked this task as not done yet:\n" +
                            "    " + task.toString() +
                            "\n" +
                            line);
                } else {
                    throw new DukeException("That's not a valid task silly!");
                }
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
            ToDos task = new ToDos(other);

            storage.add(task);
            System.out.println(
                    line +
                    "  Got it. I've added this task:\n" +
                    "    " + task.toString() + "\n" +
                    "  Now you have " + storage.size() + " tasks in your list\n" +
                    line);
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

            Deadline task = new Deadline(desc, by);

            storage.add(task);

            System.out.println(
                    line +
                    "  Got it. I've added this task:\n" +
                    "    " + task.toString() + "\n" +
                    "  Now you have " + storage.size() + " tasks in your list\n" +
                    line);
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

            Events task = new Events(desc, from, to);

            storage.add(task);

            System.out.println(
                line +
                "  Got it. I've added this task:\n" +
                "    " + task.toString() + "\n" +
                "  Now you have " + storage.size() + " tasks in your list\n" +
                line);
        } else if (command.equals("delete")) {
             /*
            deletes a task, or throws dukeException if
            given an invalid task
             */
            try {
                int tasknumber = Integer.parseInt(other);
                if (tasknumber <= storage.size()) {
                    Task task = storage.remove(tasknumber - 1);
                    System.out.println(
                        line +
                        "  Got it. I've removed this task:\n" +
                        "    " + task.toString() + "\n" +
                        "  Now you have " + storage.size() + " tasks in your list\n" +
                        line);
                } else {
                    throw new DukeException("That's not a valid task silly!");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("That's not a number silly!");
            }
        } else {
             /*
            throws a dukeException for invalid command
             */
            throw new DukeException("I don't get what that means!!");
        }
        return false;
    }

    public static void main(String[] args) {
        Emu emu = new Emu();
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        String line = "____________________________________________________________\n";

         /*
          standard start greeting for Emu
          */
        System.out.println(
            "____________________________________________________________\n" +
            " Hello, I'm Emu!\n" +
            " What can I do for you?\n" +
            "____________________________________________________________\n");

        while (!stop) {
             /*
            continues scanning, until "bye" input sets
            stop to true
             */
            String input = scanner.nextLine();
            try {
                stop = emu.respond(input);
            } catch (IOException e) {
                System.out.println(line + " UWA!!! I couldn't record that task!" + "\n" + line);
            } catch (DukeException e) {
                /*
                catches any dukeExceptions from emu.respond
                 */
                System.out.println(e.getMessage());
            }
        }
    }
}
