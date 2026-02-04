package emu;

/**
 * Defines common variables and methods shared by all task types
 * Each task has a description and a completion status
 */
public abstract class Task {
    private String description;
    private boolean isComplete;

    /**
     * Initialises the common variables shared by all tasks
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public String getStatusIcon() {
        return (isComplete ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void markComplete() {
        isComplete = true;
    }

    public void markIncomplete() {
        isComplete = false;
    }

    /**
     * Returns string for storage on hard disk
     * Subclasses may append extra information
     *
     * @return String representation used for writing to hard disk
     */
    public String toStorageString() {
        return " | " + getStatusIcon() + " | " + description;
    }

    /**
     * Returns string for display to the user
     * Subclasses may append extra information
     *
     * @return String representation used for listing
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}