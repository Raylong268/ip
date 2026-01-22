public class ToDos extends Task {
    public ToDos(String description) {
        /*
         initialises a tod0 task with all required info
         */
        super(description);
    }

    @Override
    public String record() {
        return "T" + super.record();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}