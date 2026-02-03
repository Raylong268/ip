package emu;

/**
 * Represents a to-do task that has only a description
 */
public class ToDo extends Task {
    private static final String STORAGE_MARKER = "T";
    private static final String DISPLAY_MARKER = "[T]";

    /**
     * Initialises a To-Do task
     *
     * @param description Description of the To-Do task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task
     * for storage on hard disk
     *
     * @return Storage format of a to-do task
     */
    @Override
    public String toStorageString() {
        return STORAGE_MARKER + super.toStorageString();
    }

    /**
     * Returns the string representation of the task
     * when listed to the user
     *
     * @return Display format of a to-do task
     */
    @Override
    public String toString() {
        return DISPLAY_MARKER + super.toString();
    }
}