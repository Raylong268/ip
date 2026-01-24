package emu;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
         /*
          overrides extending class Exception
          for custom message
          */
        return " UWA!!! " + super.getMessage() + "\n";
    }

    public String realMessage() {
        return super.getMessage();
    }
}