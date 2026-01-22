public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        /*
         initialises a task with all required info
         */
        this.description = description;
        this.isDone = false;
    }

    /*
     getters for statusIcon and description
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    /*
     allows a task to be marked/unmarked
     */
    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String record() {
        return " | " + getStatusIcon() + " | " + description;
    }
    /*
     string representation of a task
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
