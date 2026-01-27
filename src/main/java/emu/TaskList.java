package emu;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int number) {
        return tasks.get(number);
    }

    public int size() {
        return tasks.size();
    }

    public String list() {
        String temp = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            temp += i + "." + task.toString() + "\n";
        }
        return temp;
    }

    public String mark(int number) throws DukeException {
        if (number <= tasks.size()) {
            Task task = tasks.get(number - 1);
            task.markDone();
            return "  Nice! I've marked this task as done:\n" +
                    "    " + task.toString() + "\n";
        } else {
            throw new DukeException("That's not a valid task silly!");
        }
    }

    public String unmark(int number) throws DukeException {
        if (number <= tasks.size()) {
            Task task = tasks.get(number - 1);
            task.markUndone();
            return "  OK, I've marked this task as not done yet:\n" +
                    "    " + task.toString() + "\n";
        } else {
            throw new DukeException("That's not a valid task silly!");
        }
    }

    public String todo(String desc) {
        ToDo task = new ToDo(desc);
        tasks.add(task);
        return "  Got it. I've added this task:\n" +
                "    " + task.toString() + "\n" +
                "  Now you have " + tasks.size() + " tasks in your list\n";
    }

    public String deadline(String desc, String by) {
        Deadline task = new Deadline(desc, by);
        tasks.add(task);
        return "  Got it. I've added this task:\n" +
                "    " + task.toString() + "\n" +
                "  Now you have " + tasks.size() + " tasks in your list\n";
    }

    public String event(String desc, String from, String to) {
        Event task = new Event(desc, from, to);
        tasks.add(task);
        return "  Got it. I've added this task:\n" +
                "    " + task.toString() + "\n" +
                "  Now you have " + tasks.size() + " tasks in your list\n";
    }

    public String delete(int number) throws DukeException {
        if (number <= tasks.size()) {
            Task task = tasks.remove(number - 1);
            return "  Got it. I've removed this task:\n" +
                    "    " + task.toString() + "\n" +
                    "  Now you have " + tasks.size() + " tasks in your list\n";
        } else {
            throw new DukeException("That's not a valid task silly!");
        }
    }
}