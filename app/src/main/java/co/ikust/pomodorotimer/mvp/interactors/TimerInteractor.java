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

    void startPomodoroCountDown(OnTimerStatusObtainedListener listener);

    void startShortBreakCountDown(OnTimerStatusObtainedListener listener);

    void startLongBreakCountDown(OnTimerStatusObtainedListener listener);

    void pause(OnTimerStatusObtainedListener listener);

    void resume(OnTimerStatusObtainedListener listener);

    void stop(OnTimerStatusObtainedListener listener);

    void finishTask(OnTimerStatusObtainedListener listener);
}
