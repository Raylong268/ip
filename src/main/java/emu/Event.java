package emu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a description, start, and end date
 */
public class Event extends Task {
    private static final String STORAGE_MARKER = "E";
    private static final String DISPLAY_MARKER = "[E]";
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    private String from;
    private String to;
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Initialises an event task
     * Tries converting {@code from} and {@code to} to LocalDate
     * If fails, defaults to original string
     *
     * @param description Description of the event task
     * @param from Start date as a string
     * @param to End date as a string
     */
    public Event(String description, String from, String to) {
        super(description);

        assert from != null : "from String should not be null";
        assert !from.isEmpty() : "from String should not be empty";
        assert to != null : "to String should not be null";
        assert !to.isEmpty() : "to String should not be empty";

        try {
            this.fromDate = LocalDate.parse(from);
            this.from = fromDate.format(DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            this.from = from;
        }

        try {
            this.toDate = LocalDate.parse(to);
            this.to = toDate.format(DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            this.to = to;
        }
    }

    /**
     * Returns the string representation of the task
     * for storage on hard disk
     *
     * @return Storage format of an event task
     */
    @Override
    public String toStorageString() {
        return STORAGE_MARKER + super.toStorageString() + " | " + from + " | " + to;
    }

    /**
     * Returns the string representation of the task
     * when listed to the user
     *
     * @return Display format of an event task
     */
    @Override
    public String toString() {
        return DISPLAY_MARKER + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}