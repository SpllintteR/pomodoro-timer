package co.ikust.pomodorotimer.mvp.presenters;

import java.util.HashMap;

/**
 * Created by ivan on 16/04/15.
 */
public interface TimerPresenter {

    void onViewCreated(HashMap<String, Object> arguments);

    void onPauseTimer();

    void onResumeTimer();

    void onStopTimer();

    void onTaskDone();

    void onStartPomodoro();

    void onStartShortBreak();

    void onStartLongBreak();

}
