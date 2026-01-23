package emu;

public class ToDo extends Task {
    public ToDo(String description) {
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