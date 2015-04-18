package co.ikust.pomodorotimer.mvp.interactors;

import java.util.HashMap;

import co.ikust.pomodorotimer.rest.models.Card;
import co.ikust.pomodorotimer.storage.models.TimerStatus;

/**
 * Created by ivan on 16/04/15.
 */
public interface TimerInteractor {

    interface OnTimerStatusObtainedListener {
        void onTimerStatusObtained(TimerStatus timerStatus, Card card);
    }

    void onViewCreated(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener);

    void registerTickListener(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener);

    void unregisterTickListener();

    void showNotifications(boolean show);

    void startPomodoroCountDown(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener);

    void startShortBreakCountDown(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener);

    void startLongBreakCountDown(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener);

    void pause(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener);

    void resume(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener);

    void stop(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener);

    void finishTask(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener);
}
