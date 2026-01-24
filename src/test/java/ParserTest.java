import emu.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @ Test
    void testHandleDeadline() throws DukeException {
        // valid case
        String[] result = Parser.handleDeadline("finish homework /by tomorrow");
        assertEquals("finish homework", result[0]);
        assertEquals("tomorrow", result[1]);

        // missing /by
        try {
            Parser.handleDeadline("finish homework tomorrow");
            fail("Expected DukeException");
        } catch (DukeException e) {
            assertEquals("You forgot to put /by in your deadline task!",
                    e.realMessage());
        }

        // empty description
        try {
            Parser.handleDeadline("/by tomorrow");
            fail("Expected DukeException");
        } catch (DukeException e) {
            assertEquals(
                    "You can't make a deadline without a description and a date silly!",
                    e.realMessage());
        }

        // empty /by
        try {
            Parser.handleDeadline("finish /by ");
            fail("Expected DukeException");
        } catch (DukeException e) {
            assertEquals(
                    "You can't make a deadline without a description and a date silly!",
                    e.realMessage());
        }

        // empty
        try {
            Parser.handleDeadline("  ");
            fail("Expected DukeException");
        } catch (DukeException e) {
            assertEquals(
                    "You forgot to put /by in your deadline task!",
                    e.realMessage());
        }
    }

    @Test
    void testHandleEvent() throws DukeException {
        // valid case
        String[] result = Parser.handleEvent("meeting /from Mon /to Tue");
        assertEquals("meeting", result[0]);
        assertEquals("Mon", result[1]);
        assertEquals("Tue", result[2]);

        // /to and /from swapped
        try {
            Parser.handleEvent("meeting /to Tue /from Mon");
            fail("Expected DukeException");
        } catch (DukeException e) {
            assertEquals(
                    "You put in the wrong format! Use event (desc) /from (from) /to (to) instead!",
                    e.realMessage());
        }

        // missing /to
        try {
            Parser.handleEvent("meeting /from Mon");
            fail("Expected DukeException");
        } catch (DukeException e) {
            assertEquals(
                    "You put in the wrong format! Use event (desc) /from (from) /to (to) instead!",
                    e.realMessage());
        }

        // missing /from
        try {
            Parser.handleEvent("meeting Mon /to Tues");
            fail("Expected DukeException");
        } catch (DukeException e) {
            assertEquals(
                    "You put in the wrong format! Use event (desc) /from (from) /to (to) instead!",
                    e.realMessage());
        }

        // empty fields
        try {
            Parser.handleEvent(" /from  /to ");
            fail("Expected DukeException");
        } catch (DukeException e) {
            assertEquals(
                    "You can't make an event without a description, from date and to date silly!",
                    e.realMessage());
        }

        // empty
        try {
            Parser.handleEvent("  ");
            fail("Expected DukeException");
        } catch (DukeException e) {
            assertEquals(
                    "You put in the wrong format! Use event (desc) /from (from) /to (to) instead!",
                    e.realMessage());
        }
    }
}
