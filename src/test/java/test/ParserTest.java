package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import emu.EmuException;
import emu.Parser;

public class ParserTest {
    @ Test
    void testHandleDeadline() throws EmuException {
        // valid case
        String[] result = Parser.parseDeadline("finish homework /by tomorrow");
        assertEquals("finish homework", result[0]);
        assertEquals("tomorrow", result[1]);

        // missing /by
        try {
            Parser.parseDeadline("finish homework tomorrow");
            fail("Expected DukeException");
        } catch (EmuException e) {
            assertEquals("You forgot to put /by in your deadline task!",
                    e.realMessage());
        }

        // empty description
        try {
            Parser.parseDeadline("/by tomorrow");
            fail("Expected DukeException");
        } catch (EmuException e) {
            assertEquals(
                    "You can't make a deadline without a description and a date silly!",
                    e.realMessage());
        }

        // empty /by
        try {
            Parser.parseDeadline("finish /by ");
            fail("Expected DukeException");
        } catch (EmuException e) {
            assertEquals(
                    "You can't make a deadline without a description and a date silly!",
                    e.realMessage());
        }

        // empty
        try {
            Parser.parseDeadline("  ");
            fail("Expected DukeException");
        } catch (EmuException e) {
            assertEquals(
                    "You forgot to put /by in your deadline task!",
                    e.realMessage());
        }
    }

    @Test
    void testHandleEvent() throws EmuException {
        // valid case
        String[] result = Parser.parseEvent("meeting /from Mon /to Tue");
        assertEquals("meeting", result[0]);
        assertEquals("Mon", result[1]);
        assertEquals("Tue", result[2]);

        // /to and /from swapped
        try {
            Parser.parseEvent("meeting /to Tue /from Mon");
            fail("Expected DukeException");
        } catch (EmuException e) {
            assertEquals(
                    "You put in the wrong format! Use event (desc) /from (from) /to (to) instead!",
                    e.realMessage());
        }

        // missing /to
        try {
            Parser.parseEvent("meeting /from Mon");
            fail("Expected DukeException");
        } catch (EmuException e) {
            assertEquals(
                    "You put in the wrong format! Use event (desc) /from (from) /to (to) instead!",
                    e.realMessage());
        }

        // missing /from
        try {
            Parser.parseEvent("meeting Mon /to Tues");
            fail("Expected DukeException");
        } catch (EmuException e) {
            assertEquals(
                    "You put in the wrong format! Use event (desc) /from (from) /to (to) instead!",
                    e.realMessage());
        }

        // empty fields
        try {
            Parser.parseEvent(" /from  /to ");
            fail("Expected DukeException");
        } catch (EmuException e) {
            assertEquals(
                    "You can't make an event without a description, from date and to date silly!",
                    e.realMessage());
        }

        // empty
        try {
            Parser.parseEvent("  ");
            fail("Expected DukeException");
        } catch (EmuException e) {
            assertEquals(
                    "You put in the wrong format! Use event (desc) /from (from) /to (to) instead!",
                    e.realMessage());
        }
    }
}