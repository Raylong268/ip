package emu;

/**
 * Handles formatting of messages shown to the user
 */
public class UI {
    private static final String DIVIDER_LINE =
            "_______________________________________________________\n";
    private static final String STANDARD_GREETING =
            " Hello, I'm Emu!\n What can I do for you?\n";
    private static final String STORAGE_FAILURE =
            "UWA!!! I can't seem to find your past tasks!";

    /**
     * Returns the initial greeting message,
     * with additional message if storage failed to initialise
     *
     * @param hasStorageFailed If storage has failed to initialise
     * @return Greeting message
     */
    public String giveGreeting(boolean hasStorageFailed) {
        if (hasStorageFailed) {
            return STANDARD_GREETING + STORAGE_FAILURE;
        } else {
            return STANDARD_GREETING;
        }
    }

    /**
     * Formats a response message for display to the user
     *
     * @param response Text to be formatted
     * @return Formatted text
     */
    public String formatResponse(String response) {
        return DIVIDER_LINE + response + DIVIDER_LINE;
    }
}