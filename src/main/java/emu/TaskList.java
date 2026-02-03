package emu;

import java.util.ArrayList;

/**
 * Contains a list of tasks and provides methods
 * to manage and access them
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private static final int TASKLIST_STARTING_POINT = 0;

    /**
     * Initialises a TaskList with the given list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the task at the given position in the list
     *
     * @param position The position of the task in the list
     * @return The requested task
     */
    public Task getTask(int position) throws EmuException {
        if (position <= tasks.size() && position > TASKLIST_STARTING_POINT) {
            return tasks.get(position - 1);
        } else {
            throw new EmuException("That's not a valid task!");
        }
    }

    /**
     * Returns the number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a string representation of all tasks in the list
     */
    public String listTasks() {
        String temp = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            temp += i + ". " + task.toString() + "\n";
        }
        return temp;
    }

    /**
     * Searches for tasks containing the given substring
     * and returns a string listing them
     *
     * @param search Substring to search in task descriptions
     * @return A string of matching tasks
     */
    public String findTasks(String search) {
        int counter = 0;
        String temp = "Here are the matching tasks in your list:\n";
        for (Task task : tasks) {
            if (task.getDescription().contains(search)) {
                counter++;
                temp += counter + ". " + task.toString() + "\n";
            }
        }
        return temp;
    }

    /**
     * Marks the task at the given position as complete
     * and returns a string showing the marked task
     *
     * @param position The position of the task
     * @return Response string after marking the task
     */
    public String markTask(int position) throws EmuException {
        Task task = getTask(position);
        task.markComplete();
        return "  Nice! I've marked this task as done:\n"
                + "    " + task.toString() + "\n";
    }

    /**
     * Marks the task at the given position as incomplete
     * and returns a string showing the unmarked task
     *
     * @param position The position of the task
     * @return Response string after unmarking the task
     */
    public String unmarkTask(int position) throws EmuException {
        Task task = getTask(position);
        task.markIncomplete();
        return "  OK, I've marked this task as not done yet:\n"
                + "    " + task.toString() + "\n";
    }

    /**
     * Adds a ToDo task with the given description
     * and returns a string showing the added task
     *
     * @param desc Description of the ToDo task
     * @return Response string after adding the task
     */
    public String addToDoTask(String desc) {
        ToDo task = new ToDo(desc);
        tasks.add(task);
        return "  Got it. I've added this task:\n"
                + "    " + task.toString() + "\n"
                + "  Now you have " + tasks.size() + " tasks.\n";
    }

    /**
     * Adds a Deadline task with the given description and by date
     * and returns a string showing the added task
     *
     * @param desc Description of the Deadline task
     * @param by By date/string of the Deadline task
     * @return Response string after adding the task
     */
    public String addDeadlineTask(String desc, String by) {
        Deadline task = new Deadline(desc, by);
        tasks.add(task);
        return "  Got it. I've added this task:\n"
                + "    " + task.toString() + "\n"
                + "  Now you have " + tasks.size() + " tasks.\n";
    }

    /**
     * Adds an Event task with the given description, from date, and to date
     * and returns a string showing the added task
     *
     * @param desc Description of the Event task
     * @param from From date/string of the Event task
     * @param to To date/string of the Event task
     * @return Response string after adding the task
     */
    public String addEventTask(String desc, String from, String to) {
        Event task = new Event(desc, from, to);
        tasks.add(task);
        return "  Got it. I've added this task:\n"
                + "    " + task.toString() + "\n"
                + "  Now you have " + tasks.size() + " tasks.\n";
    }

    /**
     * Deletes the task at the given position
     * and returns a string showing the deleted task
     *
     * @param position The position of the task
     * @return Response string after deleting the task
     */
    public String deleteTask(int position) throws EmuException {
        Task task = getTask(position);
        tasks.remove(position - 1);
        return "  Got it. I've removed this task:\n"
                + "    " + task.toString() + "\n"
                + "  Now you have " + tasks.size() + " tasks.\n";
    }
}