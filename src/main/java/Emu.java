import java.util.Scanner;

public class Emu {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";

        Task[] storage = new Task[100];
        int count = 0;

        Scanner scan = new Scanner(System.in);
        System.out.println(
                line +
                " Hello, I'm Emu!\n" +
                " What can I do for you?\n" +
                line);

        while (true) {
            String fullResponse = scan.nextLine();
            String[] splitResponse = fullResponse.split(" ");
            String response = splitResponse[0];

            if (response.equals("bye")) {
                System.out.println(
                        line +
                        " Bye. Hope to see you again soon!\n" +
                        line);
                break;
            } else if (response.equals("list")) {
                int listing = 1;
                String temp = "Here are the tasks in your list: \n";
                while (listing <= count) {
                    Task task = storage[listing - 1];
                    temp += listing +
                            ".[" + task.getStatusIcon() + "] " + task.getDescription() +
                            "\n";
                    listing++;
                }
                System.out.println(line + temp + line);
            } else if (response.equals("mark")) {
                String number = splitResponse[1];
                try {
                    int tasknumber = Integer.parseInt(number);
                    if (tasknumber <= count) {
                        Task task = storage[tasknumber - 1];
                        task.markDone();
                        System.out.println(
                                line +
                                "  Nice! I've marked this task as done: \n" +
                                "    [" + task.getStatusIcon() + "] " + task.getDescription() +
                                "\n" +
                                line);
                    } else {
                        System.out.println(line + "Invalid task \n" + line);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(line + "Invalid number \n" + line);
                }
            } else if (response.equals("unmark")) {
                String number = splitResponse[1];
                try {
                    int tasknumber = Integer.parseInt(number);
                    if (tasknumber <= count) {
                        Task task = storage[tasknumber - 1];
                        task.markUndone();
                        System.out.println(
                                line +
                                "  OK, I've marked this task as not done yet: \n" +
                                "    [" + task.getStatusIcon() + "] " + task.getDescription() +
                                "\n" +
                                line);
                    } else {
                        System.out.println(line + "Invalid task \n" + line);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(line + "Invalid number \n" + line);
                }
            } else {
                System.out.println(
                        line +
                        "added: " + fullResponse + "\n" +
                        line);
                storage[count] = new Task(fullResponse);
                count++;
            }
        }
    }
}
