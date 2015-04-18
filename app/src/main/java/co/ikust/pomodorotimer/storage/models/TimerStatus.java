package co.ikust.pomodorotimer.storage.models;

/**
 * Defines the current state of the timer.
 */
public class TimerStatus {

    public enum State {
        POMODORO_COUNTDOWN,
        POMODORO_FINISHED,
        SHORT_BREAK_COUNTDOWN,
        LONG_BREAK_COUNTDOWN,
        BREAK_FINISHED,
        DONE //Timer is idle
    }

    private String taskId;

    private String listId;

    private State state;

    private boolean paused;

    private long time;

    public TimerStatus() {
        taskId = null;
        listId = null;
        state = State.DONE;
        paused = false;
        time = 0;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public long getTime() {
        return time;
    }

    public void addTime(long time) {
        this.time += time;
    }

    public void resetTime() {
        this.time = 0;
    }

}
