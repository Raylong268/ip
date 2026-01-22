public class Events extends Task {
    protected String from;
    protected String to;

    public Events(String description, String from, String to) {
        /*
         initialises an Event task with all required info
         */
        super(description);
        this.from = from;
        this.to = to;
    }

    public String record() {
        return "E" + super.record() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}