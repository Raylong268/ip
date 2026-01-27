package emu;

import java.util.Scanner;

/**
 * Manages the conversations between the chatbot and the user
 */
public class UI {
    // Line used to wrap around responses
    private String line =
            "____________________________________________________________\n";
    private Scanner scanner;

    /**
     * Initialises the UI, setting up the scanner
     * and displaying the initial greeting message.
     */
    public UI() {
        respond(" Hello, I'm Emu!\n" + " What can I do for you?\n");
        this.scanner = new Scanner(System.in);
    }

    /**
     * Manages the format of the given response and prints it
     *
     * @param response desired text to be printed
     */
    public void respond(String response) {
        System.out.println(line + response + line);
    }

    /**
     * Scans for the next response from the user and passes it
     *
     * @return The inputted message from the user
     */
    public String scan() {
        return scanner.nextLine();
    }
}