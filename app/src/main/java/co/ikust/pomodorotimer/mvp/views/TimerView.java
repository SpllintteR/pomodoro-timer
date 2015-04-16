package co.ikust.pomodorotimer.mvp.views;

/**
 * Created by ivan on 16/04/15.
 */
public interface TimerView {

    void setTaskTitle(String title);

    void setStatus(String status);

    void setCurrentTime(long currentTime);

    void setPomodoroCount(int pomodoroCount);

    void setTotalTime(long totalTime);

    void enableButtons();

    void disableButtons();

    void showPomodoroDone();

}
