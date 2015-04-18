package co.ikust.pomodorotimer.mvp.presenters;

import java.util.HashMap;

/**
 * Created by ivan on 16/04/15.
 */
public interface TimerPresenter {

    void onViewCreated(HashMap<String, Object> arguments);

    void onEnteredForeground(HashMap<String, Object> arguments);

    void onEnteredBackground();

    void onStartPomodoro(HashMap<String, Object> arguments);

    void onStartShortBreak(HashMap<String, Object> arguments);

    void onStartLongBreak(HashMap<String, Object> arguments);

    void onPauseTimer(HashMap<String, Object> arguments);

    void onResumeTimer(HashMap<String, Object> arguments);

    void onStopTimer(HashMap<String, Object> arguments);

    void onTaskDone(HashMap<String, Object> arguments);


}
