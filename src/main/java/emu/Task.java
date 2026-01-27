package emu;

/**
 * Defines common variables and methods shared by all task types.
 */
public abstract class Task {
    private String description;
    private boolean isDone; // represents if a task is completed

    /**
     * Initialises the common variables shared by all tasks.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks a task as completed
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks a task as incomplete
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns the additional parts required for the
     * string representation of a task when stored on hard disk.
     * Subclasses may override this method to provide
     * task-specific string additions
     *
     * @return String representation used for writing to hard disk
     */
    public String record() {
        return " | " + getStatusIcon() + " | " + description;
    }

    /**
     * Returns the additional parts required for the
     * string representation of a task when listed
     * Subclasses may override this method to provide
     * task-specific string additions
     *
     * @return String representation used for listing
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}