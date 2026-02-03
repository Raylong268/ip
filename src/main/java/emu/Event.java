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
        this.from = parseDateString(from);
        this.to = parseDateString(to);
    }

    /**
     * Parses a date string into a formatted date string
     * If parsing fails, returns the original string
     *
     * @param dateStr Input date string
     * @return Formatted date string if valid, otherwise original string
     */
    private String parseDateString(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return date.format(DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return dateStr;
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