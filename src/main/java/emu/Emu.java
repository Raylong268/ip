package emu;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Coordinates the UI, TaskList, Parser and Storage components
 * to handle user commands and program flow.
 */
public class Emu {
    /**
     * Controls the logic of the chatbot, executing the correct
     * command based on the String command given and returning
     * the String response for the user
     *
     * @param tasks TaskList for task-related commands
     * @param storage Storage for storing updated tasks
     * before closing the chatbot
     * @param command The command to be executed
     * @param other The extra information needed to execute the command
     * @return A string representing the response given
     * after doing the command
     */
    public String respond(TaskList tasks, Storage storage, String command, String other)
            throws EmuException {
        if (command.equals("bye")) {
            // Stores the TaskList back into Storage
            storage.resetList(tasks);
            return " Bye. Hope to see you again soon!\n";
        } else if (command.equals("list")) {
            // Lists all tasks
            return tasks.list();
        } else if (command.equals("find")) {
            // Lists all tasks that have other as a substring in the description
            return tasks.find(other);
        } else if (command.equals("mark")) {
            // Marks a task if valid, or throws EmuException if invalid task / input
            int number = Parser.handleNumber(other);
            return tasks.mark(number);
        } else if (command.equals("unmark")) {
            // Unmarks a task if valid, or throws EmuException if invalid task / input
            int number = Parser.handleNumber(other);
            return tasks.unmark(number);
        } else if (command.equals("todo")) {
            // Makes a Todo task, or throws EmuException if invalid input
            Parser.handleTodo(other);
            return tasks.todo(other);
        } else if (command.equals("deadline")) {
            // Makes a Deadline task, or throws EmuException if invalid input
            String[] items = Parser.handleDeadline(other);
            return tasks.deadline(items[0], items[1]);
        } else if (command.equals("event")) {
            // Makes an Event task, or throws EmuException if invalid input
            String[] items = Parser.handleEvent(other);
            return tasks.event(items[0], items[1], items[2]);
        } else if (command.equals("delete")) {
            // Deletes a task, or throws EmuException if invalid task / input
            int number = Parser.handleNumber(other);
            return tasks.delete(number);
        } else {
             // Throws EmuException for invalid command
            throw new EmuException("I don't get what that means!");
        }
    }

    /**
     * Initialises all the needed components, and runs the loop that
     * allows the chatbot to run
     */
    public static void main(String[] args) {
        // Initialises all the needed components
        Emu emu = new Emu();
        UI ui = new UI();
        Storage storage = new Storage("./data/tasks.txt");
        TaskList tasks;

        try {
            tasks = storage.initialiseList();
        } catch (EmuException e) {
            ui.respond(e.getMessage());
            tasks = new TaskList(new ArrayList<Task>());
        }

        while (true) {
            try {
                String input = ui.scan();
                // Parses the input into the command and other portions
                Parser parts = new Parser(input);

                // Calls for the given command and gets back the response
                String response = emu.respond(tasks, storage,
                        parts.getCommand(), parts.getOther());

                // Gets the UI to print the response
                ui.respond(response);

                if (parts.getCommand().equals("bye")) {
                    break;
                }
            } catch (EmuException e) {
                // Catches any dukeExceptions from emu.respond and displays them
                ui.respond(e.getMessage());
            }
        }
    }
}