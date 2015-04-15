package co.ikust.pomodorotimer.storage.models;

/**
 * Stores total tracked time and Pomodoros for a Trello Card (task).
 */
public class TrackedTime {

    /**
     * Total number of Pomodoros spent on the task.
     */
    private int pomodoroCount;

    /**
     * Total time (including Pomodoros and breaks) in milliseconds.
     */
    private long time;

    /**
     * Get total spent number of Pomodoros.
     *
     * @return
     */
    public int getPomodoroCount() {
        return pomodoroCount;
    }

    /**
     * Set total spent number of Pomodoros.
     *
     * @param pomodoroCount
     */
    public void setPomodoroCount(int pomodoroCount) {
        this.pomodoroCount = pomodoroCount;
    }

    /**
     * Increase total spent number of Pomodoros by one.
     */
    public void increasePomodoroCount() {
        this.pomodoroCount++;
    }

    /**
     * Get total spent time in milliseconds. Total spent time includes
     * time spent on Pomodoros (25 minutes per each) plus total time spent on breaks.
     *
     * @return total time spent on task in milliseconds
     */
    public long getTime() {
        return time;
    }

    /**
     * Set total spent time in millisecodns. Total spent time includes
     * time spent on Pomodoros (25 minutes per each) plus total time spent on breaks.
     *
     * @param time total time spent on task in milliseconds
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Append the given amount of time to total spent time. Time is given in milliseconds.
     *
     * @param additionalTime time that is appended to total time in milliseconds
     */
    public void appendTime(long additionalTime) {
        this.time += additionalTime;
    }
}
