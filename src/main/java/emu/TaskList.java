package emu;

import java.util.ArrayList;

/**
 * Contains a list of tasks and provides methods
 * to manage and access them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initialises a TaskList with the given ArrayList of Tasks
     *
     * @param tasks ArrayList of Tasks to be stored in the ArrayList of Tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the task at the given position in the ArrayList
     *
     * @param number The position of the Task in the ArrayList of Tasks
     * @return The requested task at that position
     */
    public Task get(int number) {
        return tasks.get(number);
    }

    /**
     * Returns the number of tasks stored in the ArrayList
     *
     * @return The number of tasks stored in the ArrayList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the string representation of all tasks in the ArrayList when listed
     *
     * @return A string representing the list of tasks
     */
    public String list() {
        int listing = 1;
        String temp = "Here are the tasks in your list:\n";
        while (listing <= tasks.size()) {
            Task task = tasks.get(listing - 1);
            temp += listing +
                    "." + task.toString() +
                    "\n";
            listing++;
        }
        return temp;
    }

    /**
     * Marks the task at the position of given int number
     * as completed, and responds with a String of the newly marked task
     *
     * @param number The position of the Task in the ArrayList
     * @return A string representing the response given after
     * marking the task
     */
    public String mark(int number) throws EmuException {
        if (number <= tasks.size()) {
            Task task = tasks.get(number - 1);
            task.markDone();
            return "  Nice! I've marked this task as done:\n" +
                    "    " + task.toString() + "\n";
        } else {
            throw new EmuException("That's not a valid task silly!");
        }
    }

    /**
     * Unmarks the task at the position of given int number,
     * and responds with a String of the newly unmarked task
     *
     * @param number The position of the Task in the ArrayList
     * @return A string representing the response given after
     * unmarking the task
     */
    public String unmark(int number) throws EmuException {
        if (number <= tasks.size()) {
            Task task = tasks.get(number - 1);
            task.markUndone();
            return "  OK, I've marked this task as not done yet:\n" +
                    "    " + task.toString() + "\n";
        } else {
            throw new EmuException("That's not a valid task silly!");
        }
    }

    /**
     * Adds a ToDo task to the ArrayList with the given description
     * desc, and returns a String displaying the added task
     *
     * @param desc The description of the added ToDo Task
     * @return A string representing the response given after adding the
     * ToDo task
     */
    public String todo(String desc) {
        ToDo task = new ToDo(desc);
        tasks.add(task);
        return "  Got it. I've added this task:\n" +
               "    " + task.toString() + "\n" +
               "  Now you have " + tasks.size() + " tasks in your list\n";
    }

    /**
     * Adds a Deadline task to the ArrayList with the given description
     * desc and by Date/String, and returns a String displaying the added task
     *
     * @param desc The description of the added Deadline Task
     * @param by The by Date/String of the added Deadline Task
     * @return A string representing the response given after adding the
     * Deadline task
     */
    public String deadline(String desc, String by) {
        Deadline task = new Deadline(desc, by);
        tasks.add(task);
        return "  Got it. I've added this task:\n" +
               "    " + task.toString() + "\n" +
               "  Now you have " + tasks.size() + " tasks in your list\n";
    }

    /**
     * Adds an Event task to the ArrayList with the given description
     * desc, from Date/String, and to Date/String,
     * and returns a String displaying the added task
     *
     * @param desc The description of the added Event Task
     * @param from The from Date/String of the added Event Task
     * @param to The to Date/String of the added Event Task
     * @return A string representing the response given after adding the
     * Event task
     */
    public String event(String desc, String from, String to) {
        Event task = new Event(desc, from, to);
        tasks.add(task);
        return "  Got it. I've added this task:\n" +
                "    " + task.toString() + "\n" +
                "  Now you have " + tasks.size() + " tasks in your list\n";
    }

    /**
     * Deletes the task at the position of given int number,
     * and responds with a String of the deleted task
     *
     * @param number The position of the Task in the ArrayList
     * @return A string representing the response given after
     * deleting the task
     */
    public String delete(int number) throws EmuException {
        if (number <= tasks.size()) {
            Task task = tasks.remove(number - 1);
            return "  Got it. I've removed this task:\n" +
                    "    " + task.toString() + "\n" +
                    "  Now you have " + tasks.size() + " tasks in your list\n";
        } else {
            throw new EmuException("That's not a valid task silly!");
        }
    }
}
