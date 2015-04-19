package co.ikust.pomodorotimer.mvp.views;

import java.util.HashMap;

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

    void showBreakDone();

    HashMap<String, Object> getArguments();
}
