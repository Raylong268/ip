import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    private LocalDate byDate = null;

    public Deadline(String description, String by) {
        /*
         initialises a deadline task with all required info
         */
        super(description);
        try {
            //converts the string to LocalDate
            this.byDate = LocalDate.parse(by);
            this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            //defaults to regular string if format does not fit
            this.by = by;
        }
    }

    public String record() {
        return "D" + super.record() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}