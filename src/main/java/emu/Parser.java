package emu;

public class Parser {
    private String command;
    private String other;

    public Parser(String fullResponse) {
        int firstSpace = fullResponse.indexOf(' ');
        this.command = (firstSpace == -1) ? fullResponse
                : fullResponse.substring(0, firstSpace);
        this.other = (firstSpace == -1) ? ""
                : fullResponse.substring(firstSpace + 1);
    }

    public String getCommand() {
        return this.command;
    }

    public String getOther() {
        return this.other;
    }

    public static void handleTodo(String response) throws DukeException {
        if (response.isEmpty()) {
            throw new DukeException("You can't make a todo " +
                    "without a description silly!");
        }
    }

    public static String[] handleDeadline(String response) throws DukeException {
        int slash = response.indexOf("/by");

        if (slash == -1) {
            throw new DukeException("You forgot to put /by in your deadline task!");
        }

        String desc = response.substring(0, slash).trim();
        String by = response.substring(slash + 3).trim();

        if (desc.isEmpty() || by.isEmpty()) {
            throw new DukeException("You can't make a deadline " +
                    "without a description and a date silly!");
        }
        return new String[] { desc, by };
    }

    public static String[] handleEvent(String response) throws DukeException {
        int slashfrom = response.indexOf("/from");
        int slashto = response.indexOf("/to");

        if (slashfrom == -1 || slashto == -1 || slashto < slashfrom) {
            throw new DukeException(
                    "You put in the wrong format! " +
                            "Use event (desc) /from (from) /to (to) instead!");
        }

        String desc = response.substring(0, slashfrom).trim();
        String from = response.substring(slashfrom + 5, slashto).trim();
        String to = response.substring(slashto + 3).trim();

        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException("You can't make an event " +
                    "without a description, from date and to date silly!");
        }
        return new String[] { desc, from, to };
    }

    public static int handleNumber(String stringNumber) throws DukeException{
        try {
            return Integer.parseInt(stringNumber);
        } catch (NumberFormatException e) {
            throw new DukeException("That's not a valid number silly!");
        }
    }
}