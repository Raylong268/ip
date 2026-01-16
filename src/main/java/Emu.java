import java.util.Scanner;

public class Emu {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
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
            } else {
                System.out.println(line + response + "\n" + line);
            }
        }
    }
}
