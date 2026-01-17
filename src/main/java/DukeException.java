public class DukeException extends Exception {
    String line = "____________________________________________________________\n";

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return line + " UWA!!! " + super.getMessage() + "\n" + line;
    }
}