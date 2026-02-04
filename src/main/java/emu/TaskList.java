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
        assert tasks != null : "tasks should not be null";
        this.tasks = tasks;
    }

    /**
     * Returns the task at the given position in the ArrayList
     *
     * @param number The position of the Task in the ArrayList of Tasks
     * @return The requested task at that position
     */
    public Task get(int number) {
        assert number >= 0 : "number must not be negative";
        assert number < tasks.size() : "number out of bounds of TaskList";

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
        String temp = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);

            assert task != null : "task should not be null";
            temp += i + "." + task.toString() + "\n";
        }
        return temp;
    }

    /**
     * Searches for all tasks in ArrayList that have the substring search
     * in their description and returns a String that lists them
     *
     * @param search Substring to look for in the task description
     * @return String representation of all the found tasks with the substring
     */
    public String find(String search) {
        assert search != null : "search must not be null";

        int counter = 0;
        String temp = "Here are the matching tasks in your list:\n";
        for (int i = 1; i <= size(); i++) {
            Task task = tasks.get(i - 1);
            assert task != null : "task should not be null";

            if (task.getDescription().contains(search)) {
                temp += counter + "." + task.toString() + "\n";
                counter++;
            }
        }
        return temp;
    }

    /**
     * Marks the task at the position of given int number
     * as completed, and responds with a String of the newly marked task
     *
     * @param number The position of the Task in the ArrayList
     * @return A string representing the response given after marking the task
     */
    public String mark(int number) throws EmuException {
        if (number <= tasks.size() && number >= 1) {
            Task task = tasks.get(number - 1);
            assert task != null : "task should not be null";

            task.markDone();
            return "  Nice! I've marked this task as done:\n"
                    + "    " + task.toString() + "\n";
        } else {
            throw new EmuException("That's not a valid task silly!");
        }
    }

    /**
     * Unmarks the task at the position of given int number,
     * and responds with a String of the newly unmarked task
     *
     * @param number The position of the Task in the ArrayList
     * @return A string representing the response given after unmarking the task
     */
    public String unmark(int number) throws EmuException {
        if (number <= tasks.size() && number >= 1) {
            Task task = tasks.get(number - 1);
            assert task != null : "task should not be null";

            task.markUndone();
            return "  OK, I've marked this task as not done yet:\n"
                    + "    " + task.toString() + "\n";
        } else {
            throw new EmuException("That's not a valid task silly!");
        }
    }

    /**
     * Adds a ToDo task to the ArrayList with the given description
     * desc, and returns a String displaying the added task
     *
     * @param desc The description of the added ToDo Task
     * @return A string representing the response given after adding the ToDo task
     */
    public String todo(String desc) {
        assert desc != null : "desc cannot be null";
        assert !desc.isEmpty() : "desc cannot be empty";

        ToDo task = new ToDo(desc);
        tasks.add(task);
        return "  Got it. I've added this task:\n" + "    " + task.toString() + "\n"
                + "  Now you have " + tasks.size() + " tasks in your list\n";
    }

    /**
     * Adds a Deadline task to the ArrayList with the given description
     * desc and by Date/String, and returns a String displaying the added task
     *
     * @param desc The description of the added Deadline Task
     * @param by The by Date/String of the added Deadline Task
     * @return A string representing the response given after adding the Deadline task
     */
    public String deadline(String desc, String by) {
        assert desc != null : "desc cannot be null";
        assert !desc.isEmpty() : "desc cannot be empty";

        assert by != null : "by cannot be null";
        assert !by.isEmpty() : "by cannot be empty";

        Deadline task = new Deadline(desc, by);
        tasks.add(task);
        return "  Got it. I've added this task:\n" + "    " + task.toString() + "\n"
                + "  Now you have " + tasks.size() + " tasks in your list\n";
    }

    /**
     * Adds an Event task to the ArrayList with the given description
     * desc, from Date/String, and to Date/String,
     * and returns a String displaying the added task
     *
     * @param desc The description of the added Event Task
     * @param from The from Date/String of the added Event Task
     * @param to The to Date/String of the added Event Task
     * @return A string representing the response given after adding the Event task
     */
    public String event(String desc, String from, String to) {
        assert desc != null : "desc cannot be null";
        assert !desc.isEmpty() : "desc cannot be empty";

        assert from != null : "from cannot be null";
        assert !from.isEmpty() : "from cannot be empty";

        assert to != null : "to cannot be null";
        assert !to.isEmpty() : "to cannot be empty";

        Event task = new Event(desc, from, to);
        tasks.add(task);
        return "  Got it. I've added this task:\n"
                + "    " + task.toString() + "\n"
                + "  Now you have " + tasks.size() + " tasks in your list\n";
    }

    /**
     * Deletes the task at the position of given int number,
     * and responds with a String of the deleted task
     *
     * @param number The position of the Task in the ArrayList
     * @return A string representing the response given after deleting the task
     */
    public String delete(int number) throws EmuException {
        if (number <= tasks.size() && number >= 1) {
            Task task = tasks.remove(number - 1);
            assert task != null : "task should not be null";

            return "  Got it. I've removed this task:\n" + "    " + task.toString() + "\n"
                    + "  Now you have " + tasks.size() + " tasks in your list\n";
        } else {
            throw new EmuException("That's not a valid task silly!");
        }
    }
}