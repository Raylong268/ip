package emu;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Emu {
    public String respond(TaskList tasks, Storage storage,
                          String command, String other) throws DukeException, IOException {
        if (command.equals("bye")) {
            storage.resetList(tasks);
            return " Bye. Hope to see you again soon!\n";
        } else if (command.equals("list")) {
            return tasks.list();
        } else if (command.equals("find")) {
            // Lists all tasks that have other as a substring in the description
            return tasks.find(other);
        } else if (command.equals("mark")) {
            int number = Parser.handleNumber(other);
            return tasks.mark(number);
        } else if (command.equals("unmark")) {
            int number = Parser.handleNumber(other);
            return tasks.unmark(number);
        } else if (command.equals("todo")) {
             /*
            makes a ToD0 task, or throws dukeException if
            given no valid input
             */
            Parser.handleTodo(other);
            return tasks.todo(other);
        } else if (command.equals("deadline")) {
             /*
            makes a deadline task, or throws dukeException if
            given no valid input
             */
            String[] items = Parser.handleDeadline(other);
            return tasks.deadline(items[0], items[1]);
        } else if (command.equals("event")) {
             /*
            makes an event task, or throws dukeException if
            given no valid input
             */
            String[] items = Parser.handleEvent(other);
            return tasks.event(items[0], items[1], items[2]);
        } else if (command.equals("delete")) {
             /*
            deletes a task, or throws dukeException if
            given an invalid task
             */
            int number = Parser.handleNumber(other);
            return tasks.delete(number);
        } else {
             /*
            throws a dukeException for invalid command
             */
            throw new DukeException("I don't get what that means!!");
        }
    }

    public static void main(String[] args) {
        Emu emu = new Emu();
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;

        UI ui = new UI();
        Storage storage = new Storage("./data/tasks.txt");
        TaskList tasks;
        try {
            tasks = storage.initialiseList();
        } catch (DukeException e) {
            ui.respond(e.getMessage());
            tasks = new TaskList(new ArrayList<Task>());
        }

        while (!stop) {
             /*
            continues scanning, until "bye" input sets
            stop to true
             */
            try {
                String input = ui.scan();
                Parser parts = new Parser(input);
                String response = emu.respond(tasks, storage,
                                              parts.getCommand(), parts.getOther());
                ui.respond(response);
                if (parts.getCommand().equals("bye")) {
                    break;
                }
            } catch (IOException e) {
                ui.respond(" UWA!!! I couldn't record that task!" + "\n");
            } catch (DukeException e) {
                /*
                catches any dukeExceptions from emu.respond
                 */
                ui.respond(e.getMessage());
            }
        }
    }
}
