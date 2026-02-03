package emu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event Task with a description, from Date/String,
 * and to Date/String, inheriting from parent class Task
 */
public class Event extends Task {
    private String from;
    private String to;
    private LocalDate fromDate; //LocalDate version of from Date
    private LocalDate toDate; //LocalDate version of to Date

    /**
     * Initialises an Event task. Tries converting String from
     * and String to a LocalDate, if fails it defaults to the String itself.
     *
     * @param description description of Event Task
     * @param from from Date/String of Event Task
     * @param to to Date/String of Event Task
     */
    public Event(String description, String from, String to) {
        super(description); // calls parent Task constructor

        assert from != null : "from String should not be null";
        assert !from.isEmpty() : "from String should not be empty";
        assert to != null : "to String should not be null";
        assert !to.isEmpty() : "to String should not be empty";

        try {
            // tries converting the string to LocalDate
            this.fromDate = LocalDate.parse(from);
            this.from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            // defaults to regular string if conversion fails
            this.from = from;
        }

        try {
            // tries converting the string to LocalDate
            this.toDate = LocalDate.parse(to);
            this.to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            // defaults to regular string if conversion fails
            this.to = to;
        }
    }

    /**
     * Creates the string representation of the task
     * in hard disk
     *
     * @return String representation of an Event task used for writing to hard disk
     */
    public String record() {
        return "E" + super.record() + " | " + from + " | " + to;
    }

    /**
     * Creates the string representation of the task
     * when listed
     *
     * @return String representation of an Event task when listed
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}