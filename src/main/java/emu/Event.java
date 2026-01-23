package emu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String from;
    protected String to;
    private LocalDate fromDate = null;
    private LocalDate toDate = null;

    public Event(String description, String from, String to) {
        /*
         initialises an duke.Event task with all required info
         */
        super(description);

        try {
            //converts the string to LocalDate
            this.fromDate = LocalDate.parse(from);
            this.from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            //defaults to regular string if format does not fit
            this.from = from;
        }

        try {
            //converts the string to LocalDate
            this.toDate = LocalDate.parse(to);
            this.to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            //defaults to regular string if format does not fit
            this.to = to;
        }
    }

    public String record() {
        return "E" + super.record() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}