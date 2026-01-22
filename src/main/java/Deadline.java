public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        /*
         initialises a deadline task with all required info
         */
        super(description);
        this.by = by;
    }

    public String record() {
        return "D" + super.record() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}