package emu;

import java.util.ArrayList;

/**
 * Contains a list of tasks and provides methods
 * to manage and access them
 */
public class TaskList {
    private static final int TASKLIST_STARTING_POINT = 0;

    private final ArrayList<Task> tasks;
    private final ArrayList<String> history;

    /**
     * Initialises a TaskList with the given list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "tasks should not be null";
        this.tasks = tasks;
        this.history = new ArrayList<String>();
    }

    /**
     * Returns the task at the given position in the list
     *
     * @param position The position of the task in the list
     * @return The requested task
     */
    public Task getTask(int position) throws EmuException {
        if (position <= tasks.size() && position >= TASKLIST_STARTING_POINT) {
            Task task = tasks.get(position);
            assert task != null : "task should not be null";
            return task;
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
            assert task != null : "task should not be null";
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
        assert search != null : "search must not be null";

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
        Task task = getTask(position - 1);
        if (task.getStatusIcon().equals(" ")) {
            task.markComplete();
            history.add("mark " + position);
            return "  Nice! I've marked this task as done:\n"
                    + "    " + task.toString() + "\n";
        } else {
            throw new EmuException("The task is already marked!");
        }
    }

    /**
     * Marks the task at the given position as incomplete
     * and returns a string showing the unmarked task
     *
     * @param position The position of the task
     * @return Response string after unmarking the task
     */
    public String unmarkTask(int position) throws EmuException {
        Task task = getTask(position - 1);
        if (task.getStatusIcon().equals("X")) {
            task.markIncomplete();
            history.add("unmark " + position);
            return "  OK, I've marked this task as not done yet:\n"
                    + "    " + task.toString() + "\n";
        } else {
            throw new EmuException("The task is already unmarked!");
        }
    }

    /**
     * Adds a ToDo task with the given description
     * and returns a string showing the added task
     *
     * @param desc Description of the ToDo task
     * @return Response string after adding the task
     */
    public String addToDoTask(String desc) {
        assert desc != null : "desc cannot be null";
        assert !desc.isEmpty() : "desc cannot be empty";

        ToDo task = new ToDo(desc);
        tasks.add(task);
        history.add("add");
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
        assert desc != null : "desc cannot be null";
        assert !desc.isEmpty() : "desc cannot be empty";

        assert by != null : "by cannot be null";
        assert !by.isEmpty() : "by cannot be empty";

        Deadline task = new Deadline(desc, by);
        tasks.add(task);
        history.add("add");
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
        assert desc != null : "desc cannot be null";
        assert !desc.isEmpty() : "desc cannot be empty";

        assert from != null : "from cannot be null";
        assert !from.isEmpty() : "from cannot be empty";

        assert to != null : "to cannot be null";
        assert !to.isEmpty() : "to cannot be empty";

        Event task = new Event(desc, from, to);
        tasks.add(task);
        history.add("add");
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
        Task task = getTask(position - 1);
        tasks.remove(position - 1);
        history.add("delete " + task.toStorageString());
        return "  Got it. I've removed this task:\n"
                + "    " + task.toString() + "\n"
                + "  Now you have " + tasks.size() + " tasks.\n";
    }

    /**
     * Undoes the previous edit to the Tasklist
     * and returns a string showing the undone task
     *
     * @return Response string representing the undone task
     */
    public String undoLastCommand() throws EmuException {
        if (history.isEmpty()) {
            throw new EmuException("There's nothing to undo!");
        }

        String lastCommand = history.get(history.size() - 1);
        history.remove(history.size() - 1);

        String[] parts = lastCommand.split(" ", 2);
        String command = parts[0];
        String other = parts.length > 1 ? parts[1] : "";

        String response = "Ok! I've undone the task as specified below!\n";

        if (command.equals("add")) {
            Task task = tasks.get(tasks.size() - 1);
            tasks.remove(tasks.size() - 1);
            response += "Last added task has been removed: " + task.toString() + "\n";

        } else if (command.equals("unmark")) {
            int position = Parser.parseNumber(other);
            Task task = tasks.get(position - 1);
            task.markComplete();
            response += "Task has been re-marked: " + task.toString() + "\n";

        } else if (command.equals("mark")) {
            int position = Parser.parseNumber(other);
            Task task = tasks.get(position - 1);
            task.markIncomplete();
            response += "Task has been unmarked: " + task.toString() + "\n";

        } else if (command.equals("delete")) {
            Task task = Storage.parseTask(other);
            tasks.add(task);
            response += "Deleted task has been restored: " + task.toString() + "\n";

        } else {
            throw new EmuException("There's nothing to undo!");
        }

        return response;
    }
}