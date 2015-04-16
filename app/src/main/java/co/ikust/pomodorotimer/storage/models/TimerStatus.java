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
        BREAK_FINISHED
    }

    private String taskId;

    private State state;

    private boolean paused;


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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
}
