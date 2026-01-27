package emu;

/**
 * Parses the text inputted by the user
 * to make sense of the command
 */
public class Parser {
    private String command; // The portion that dictates the method used
    private String other; // The portion parsed and inputted into the method

    /**
     * Initialises the Parser with the response provided by the user,
     * and splits it into it's command and other portions
     *
     * @param fullResponse The response given from the user
     */
    public Parser(String fullResponse) {
        int firstSpace = fullResponse.indexOf(' ');
        this.command = (firstSpace == -1)
                ? fullResponse
                : fullResponse.substring(0, firstSpace);
        this.other = (firstSpace == -1)
                ? ""
                : fullResponse.substring(firstSpace + 1);
    }

    public String getCommand() {
        return this.command;
    }

    public String getOther() {
        return this.other;
    }

    /**
     * Validates the String response for making a ToDo task
     *
     * @param response The provided string to be verified to make a Deadline task
     * @throws EmuException If String response is invalid for a ToDo task
     */
    public static void handleTodo(String response) throws EmuException {
        if (response.isEmpty()) {
            throw new EmuException("You can't make a todo without a description silly!");
        }
    }

    /**
     * Validates and parses the given String response for making
     * a Deadline task.
     *
     * @param response The provided string to be parsed to make a Deadline task
     * @return A string array containing the parsed response used for
     * making a Deadline Task
     * @throws EmuException If String response is invalid for a Deadline task
     */
    public static String[] handleDeadline(String response) throws EmuException {
        int slash = response.indexOf("/by");

        if (slash == -1) {
            throw new EmuException("You forgot to put /by in your deadline task!");
        }

        String desc = response.substring(0, slash).trim();
        String by = response.substring(slash + 3).trim();

        if (desc.isEmpty() || by.isEmpty()) {
            throw new EmuException("You can't make a deadline without a description and a date silly!");
        }
        return new String[] { desc, by };
    }

    /**
     * Validates and parses the given String response for making
     * an Event task.
     *
     * @param response The provided string to be parsed to make an Event task
     * @return A string array containing the parsed response used for
     * making an Event Task
     * @throws EmuException If String response is invalid for an Event task
     */
    public static String[] handleEvent(String response) throws EmuException {
        int slashFrom = response.indexOf("/from");
        int slashTo = response.indexOf("/to");

        if (slashFrom == -1 || slashTo == -1 || slashTo < slashFrom) {
            throw new EmuException("You put in the wrong format! "
                    + "Use event (desc) /from (from) /to (to) instead!");
        }

        String desc = response.substring(0, slashFrom).trim();
        String from = response.substring(slashFrom + 5, slashTo).trim();
        String to = response.substring(slashTo + 3).trim();

        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new EmuException("You can't make an event without a description, "
                    + "from date and to date silly!");
        }
        return new String[] { desc, from, to };
    }

    /**
     * Validates the stringNumber given in the response is a
     * valid number before turning it into an int
     *
     * @param stringNumber The provided string to be converted to an int
     * @return The int created from converting the string
     * @throws EmuException If String stringNumber is not actually an int
     */
    public static int handleNumber(String stringNumber) throws EmuException {
        try {
            return Integer.parseInt(stringNumber);
        } catch (NumberFormatException e) {
            throw new EmuException("That's not a valid number silly!");
        }
    }
}