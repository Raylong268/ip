package emu;

/**
 * Represents a ToDo Task that only has a description,
 * inherits from parent class Task
 */
public class ToDo extends Task {
    /**
     * Initialises a ToDo task
     *
     * @param description description of ToDo Task
     */
    public ToDo(String description) {
        super(description); // calls parent Task constructor
    }

    /**
     * Creates the string representation of the task
     * in hard disk
     *
     * @return String representation of a ToDo task used for writing to
     * hard disk
     */
    @Override
    public String record() {
        return "T" + super.record();
    }

    /**
     * Creates the string representation of the task
     * when listed
     *
     * @return String representation of a ToDo task when listed
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}