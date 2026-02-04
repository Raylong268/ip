package emu;

/**
 * Manages the conversation format between the chatbot and the user
 */
public class UI {
    // Line used to wrap around responses
    private String line =
            "_______________________________________________________\n";

    /**
     * Returns the initial greeting message.
     *
     * @return String representation of greeting
     */
    public String greeting() {
        return " Hello, I'm Emu!\n" + " What can I do for you?\n";
    }

    /**
     * Manages the format of the given response and
     * returns the correct string
     *
     * @param response Text to be formatted
     * @return Formatted text
     */
    public String format(String response) {
        assert response != null : "response should not be null";
        assert !response.isEmpty() : "response should not be empty";

        return line + response + line;
    }
}