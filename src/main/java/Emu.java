import java.util.Scanner;

public class Emu {
    private Task[] storage;
    int count = 0;
    String line = "____________________________________________________________\n";

    public Emu() {
        storage = new Task[100];
        count = 0;
    }

    public boolean respond(String fullResponse) throws DukeException {
        int firstSpace = fullResponse.indexOf(' ');
        String command = (firstSpace == -1) ? fullResponse
                : fullResponse.substring(0, firstSpace);
        String other = (firstSpace == -1) ? ""
                : fullResponse.substring(firstSpace + 1);
        if (command.equals("bye")) {
            System.out.println(
                    line +
                    " Bye. Hope to see you again soon!\n" +
                    line);
            return false;
        } else if (command.equals("list")) {
            int listing = 1;
            String temp = "Here are the tasks in your list:\n";
            while (listing <= count) {
                Task task = storage[listing - 1];
                temp += listing +
                        "." + task.toString() +
                        "\n";
                listing++;
            }
            System.out.println(line + temp + line);
        } else if (command.equals("mark")) {
            try {
                int tasknumber = Integer.parseInt(other);
                if (tasknumber <= count) {
                    Task task = storage[tasknumber - 1];
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
            try {
                int tasknumber = Integer.parseInt(other);
                if (tasknumber <= count) {
                    Task task = storage[tasknumber - 1];
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
            if (other.equals("")) {
                throw new DukeException("You can't make a todo without a description silly!");
            }
            ToDos task = new ToDos(other);
            storage[count] = task;
            count++;
            System.out.println(
                    line +
                    "  Got it. I've added this task:\n" +
                    "    " + task.toString() + "\n" +
                    "  Now you have " + count + " tasks in your list\n" +
                    line);
        } else if (command.equals("deadline")) {
            if (other.equals("")) {
                throw new DukeException("You can't make a todo without a description silly!");
            }
            int slash = other.indexOf("/by");
            String desc = other.substring(0, slash - 1);
            String by = other.substring(slash + 4);
            Deadline task = new Deadline(desc, by);
            storage[count] = task;
            count++;
            System.out.println(
                    line +
                    "  Got it. I've added this task:\n" +
                    "    " + task.toString() + "\n" +
                    "  Now you have " + count + " tasks in your list\n" +
                    line);
        } else if (command.equals("event")) {
            if (other.equals("")) {
                throw new DukeException("You can't make a todo without a description silly!");
            }
            int slashfrom = other.indexOf("/from");
            int slashto = other.indexOf("/to");
            String desc = other.substring(0, slashfrom - 1);
            String from = other.substring(slashfrom + 6, slashto - 1);
            String to = other.substring(slashto + 4);
            Events task = new Events(desc, from, to);
            storage[count] = task;
            count++;
            System.out.println(
                line +
                "  Got it. I've added this task:\n" +
                "    " + task.toString() + "\n" +
                "  Now you have " + count + " tasks in your list\n" +
                line);
        } else {
            throw new DukeException("I'm sorry, but I don't get what that means :-(");
        }
        return true;
    }

    public static void main(String[] args) {
        Emu emu = new Emu();
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;

        System.out.println(
            "____________________________________________________________\n" +
            " Hello, I'm Emu!\n" +
            " What can I do for you?\n" +
            "____________________________________________________________\n");

        while (!stop) {
            String input = scanner.nextLine();
            try {
                stop = emu.respond(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
