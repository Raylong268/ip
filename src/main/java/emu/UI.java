package emu;

import java.util.Scanner;

public class UI {
    private String line =
            "____________________________________________________________\n";
    private Scanner scanner;

    public UI() {
        respond(" Hello, I'm Emu!\n" + " What can I do for you?\n");
        this.scanner = new Scanner(System.in);
    }

    public void respond(String command) {
        System.out.println(line + command + line);
    }

    public String scan() {
        return scanner.nextLine();
    }
}