package emu;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Coordinates the UI, TaskList, Parser and Storage components
 * to handle user commands and program flow.
 */
public class Emu {
    private Storage storage;
    private TaskList tasks;
    private UI ui;
    private boolean isExit; // Represents if the chatbot needs to close

    public Emu() {
        // Initialises the storage, UI and TaskList
        this.storage = new Storage("./data/tasks.txt");
        this.ui = new UI();
        this.tasks = new TaskList(new ArrayList<Task>());
        this.isExit = false;
    }

    /**
     * Initialises the TaskList for Emu and returns any error messages
     *
     * @return A string representing if the initialisation failed,
     * otherwise a string representing the standard greeting
     */
    public String initialiseTaskList() {
        try {
            this.tasks = storage.initialiseList();
            return ui.format(ui.greeting());
        } catch (EmuException e) {
            return ui.format(ui.greeting() + e.getMessage());
        }
    }

    public boolean getExitStatus() {
        return this.isExit;
    }

    /**
     * Controls the logic of the chatbot, executing the correct
     * command based on the String input given and returning
     * the formatted String response for the user
     *
     * @param input The input given by the user
     * @return A string representing the response given
     * after doing the command
     */
    public String respond(String input) {
        // Parses the input into command and other portions
        Parser parts = new Parser(input);
        String command = parts.getCommand();
        String other = parts.getOther();

        try {
            String response;
            if (command.equals("bye")) {
                // Stores the TaskList back into Storage
                storage.resetList(tasks);
                this.isExit = true;
                response = " Bye. Hope to see you again soon!\n";
            } else if (command.equals("list")) {
                // Lists all tasks
                response = tasks.list();
            } else if (command.equals("find")) {
                // Lists all tasks that have other as a substring in the description
                response = tasks.find(other);
            } else if (command.equals("mark")) {
                // Marks a task if valid, or throws EmuException if invalid task / input
                int number = Parser.handleNumber(other);
                response = tasks.mark(number);
            } else if (command.equals("unmark")) {
                // Unmarks a task if valid, or throws EmuException if invalid task / input
                int number = Parser.handleNumber(other);
                response = tasks.unmark(number);
            } else if (command.equals("todo")) {
                // Makes a Todo task, or throws EmuException if invalid input
                Parser.handleTodo(other);
                response = tasks.todo(other);
            } else if (command.equals("deadline")) {
                // Makes a Deadline task, or throws EmuException if invalid input
                String[] items = Parser.handleDeadline(other);
                response = tasks.deadline(items[0], items[1]);
            } else if (command.equals("event")) {
                // Makes an Event task, or throws EmuException if invalid input
                String[] items = Parser.handleEvent(other);
                response = tasks.event(items[0], items[1], items[2]);
            } else if (command.equals("delete")) {
                // Deletes a task, or throws EmuException if invalid task / input
                int number = Parser.handleNumber(other);
                response = tasks.delete(number);
            } else {
                // Throws EmuException for invalid command
                throw new EmuException("I don't get what that means!");
            }

            return ui.format(response);
        } catch (EmuException e) {
            return ui.format(e.getMessage());
        }
    }
}