import java.util.Scanner;

public class Emu {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";

        String[] storage = new String[100];
        int count = 0;

        Scanner scan = new Scanner(System.in);
        System.out.println(
                line +
                " Hello, I'm Emu!\n" +
                " What can I do for you?\n" +
                line);
        while (true) {
            String response = scan.nextLine();
            if (response.equals("bye")) {
                System.out.println(
                        line +
                        " Bye. Hope to see you again soon!\n" +
                        line);
                break;
            }
            else if (response.equals("list")) {
                int listing = 1;
                String temp = "";
                while (listing <= count) {
                    temp += listing + ". " + storage[listing] + "\n";
                    listing++;
                }
                System.out.println(line + temp + line);
            } else {
                System.out.println(
                        line +
                        "added: " + response + "\n" +
                        line);
                storage[count] = response;
                count++;
            }
        }
    }
}
