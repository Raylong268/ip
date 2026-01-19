import java.util.Scanner;
import java.util.ArrayList;

public class Emu {
    private ArrayList<Task> storage;
    String line = "____________________________________________________________\n";

    public Emu() {
        /*
        initialises a new ArrayList storage
         */
        storage = new ArrayList<>();
    }

    public boolean respond(String fullResponse) throws DukeException {
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
            if (other.equals("")) {
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
            if (other.equals("")) {
                throw new DukeException("You can't make a deadline " +
                        "without a description silly!");
            }
            int slash = other.indexOf("/by");
            String desc = other.substring(0, slash - 1);
            String by = other.substring(slash + 4);
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
            if (other.equals("")) {
                throw new DukeException("You can't make an event without a description silly!");
            }
            int slashfrom = other.indexOf("/from");
            int slashto = other.indexOf("/to");
            String desc = other.substring(0, slashfrom - 1);
            String from = other.substring(slashfrom + 6, slashto - 1);
            String to = other.substring(slashto + 4);
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
            } catch (DukeException e) {
                /*
                catches any dukeExceptions from emu.respond
                 */
                System.out.println(e.getMessage());
            }
        }
    }
}
