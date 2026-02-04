package emu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline Task with a description and by Date/String,
 * inheriting from parent class Task
 */
public class Deadline extends Task {
    private String by;
    private LocalDate byDate; //LocalDate version of by Date

    /**
     * Initialises a Deadline task. Tries converting String by to
     * a LocalDate, if fails it defaults to the String by itself.
     *
     * @param description description of Deadline Task
     * @param by by Date/String of Deadline Task
     */
    public Deadline(String description, String by) {
        super(description); // calls parent Task constructor

        assert by != null : "by String should not be null";

        try {
            // tries converting the string to LocalDate
            this.byDate = LocalDate.parse(by);
            this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            // defaults to regular string if conversion fails
            this.by = by;
        }
    }

    /**
     * Creates the string representation of the task
     * in hard disk
     *
     * @return String representation of a Deadline task used for writing to hard disk
     */
    public String record() {
        return "D" + super.record() + " | " + by;
    }

    /**
     * Creates the string representation of the task
     * when listed
     *
     * @return String representation of a Deadline task when listed
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}