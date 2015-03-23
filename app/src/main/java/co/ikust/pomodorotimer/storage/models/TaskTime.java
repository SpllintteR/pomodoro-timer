package co.ikust.pomodorotimer.storage.models;

import co.ikust.pomodorotimer.rest.models.Card;

/**
 * Stores total tracked time and Pomodoros for a Trello Card (task).
 */
public class TaskTime {

    //TODO enum that defines the state of the task ("do to", "doing", "done")

    /**
     * Trello Card that represents the task time is tracked for.
     */
    private Card card;

    /**
     * Total number of Pomodoros spent on the task.
     */
    private int pomodoroCount;

    /**
     * Total time (including Pomodoros and breaks) in milliseconds.
     */
    private long time;

    /**
     * Create new TaskTime that will be used to track time for the given {@link co.ikust.pomodorotimer.rest.models.Card}.
     *
     * @param card Trello Card that represents task time will be tracked for
     */
    public TaskTime(Card card) {
        this.card = card;
    }

    /**
     * Get the associated Trello card.
     *
     * @return
     */
    public Card getCard() {
        return card;
    }

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
