package co.ikust.pomodorotimer.mvp.interactors.impl;

import java.util.HashMap;

import co.ikust.pomodorotimer.TimerService;
import co.ikust.pomodorotimer.activities.TimerActivity;
import co.ikust.pomodorotimer.mvp.interactors.TimerInteractor;
import co.ikust.pomodorotimer.rest.models.Card;
import co.ikust.pomodorotimer.storage.models.TimerStatus;
import co.ikust.pomodorotimer.utils.TimerServiceUtils;

/**
 * Created by ivan on 16/04/15.
 */
public class TimerInteractorImpl implements TimerInteractor {

    @Override
    public void onViewCreated(HashMap<String, Object> arguments, final OnTimerStatusObtainedListener listener) {
        final Card card = (Card) arguments.get(TimerActivity.EXTRA_CARD);

        TimerServiceUtils.startTimerService(new TimerServiceUtils.OnServiceStartedListener() {
            @Override
            public void onServiceStarted() {
                if(listener != null) {
                    listener.onTimerStatusObtained(
                            TimerService.getInstance().getTimerStatus(),
                            card
                    );
                }
            }
        });
    }

    @Override
    public void registerTickListener(HashMap<String, Object> arguments, final OnTimerStatusObtainedListener listener) {
        final Card card = (Card) arguments.get(TimerActivity.EXTRA_CARD);

        TimerService.getInstance().registerListener(new TimerService.TimerServiceListener() {
            @Override
            public void onTimerStatusChanged(TimerStatus status) {
                if(listener != null) {
                    listener.onTimerStatusObtained(status, card);
                }
            }
        });
    }

    @Override
    public void unregisterTickListener() {
        TimerService.getInstance().unregisterListener();
    }

    @Override
    public void showNotifications(boolean show) {
        TimerService.getInstance().showNotifications(show);
    }

    @Override
    public void startPomodoroCountDown(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener) {
        Card card = (Card) arguments.get(TimerActivity.EXTRA_CARD);
        TimerService.getInstance().startCountDown(card, TimerStatus.State.POMODORO_COUNTDOWN);

        if(listener != null) {
            listener.onTimerStatusObtained(TimerService.getInstance().getTimerStatus(), card);
        }
    }

    @Override
    public void startShortBreakCountDown(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener) {
        Card card = (Card) arguments.get(TimerActivity.EXTRA_CARD);
        TimerService.getInstance().startCountDown(card, TimerStatus.State.SHORT_BREAK_COUNTDOWN);

        if(listener != null) {
            listener.onTimerStatusObtained(TimerService.getInstance().getTimerStatus(), card);
        }
    }

    @Override
    public void startLongBreakCountDown(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener) {
        Card card = (Card) arguments.get(TimerActivity.EXTRA_CARD);
        TimerService.getInstance().startCountDown(card, TimerStatus.State.LONG_BREAK_COUNTDOWN);

        if(listener != null) {
            listener.onTimerStatusObtained(TimerService.getInstance().getTimerStatus(), card);
        }
    }

    @Override
    public void pause(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener) {
        Card card = (Card) arguments.get(TimerActivity.EXTRA_CARD);
        TimerService.getInstance().pause();

        if(listener != null) {
            listener.onTimerStatusObtained(TimerService.getInstance().getTimerStatus(), card);
        }
    }

    @Override
    public void resume(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener) {
        Card card = (Card) arguments.get(TimerActivity.EXTRA_CARD);
        TimerService.getInstance().resume();

        if(listener != null) {
            listener.onTimerStatusObtained(TimerService.getInstance().getTimerStatus(), card);
        }
    }

    @Override
    public void stop(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener) {
        Card card = (Card) arguments.get(TimerActivity.EXTRA_CARD);
        TimerService.getInstance().stop();

        if(listener != null) {
            listener.onTimerStatusObtained(TimerService.getInstance().getTimerStatus(), card);
        }
    }

    @Override
    public void finishTask(HashMap<String, Object> arguments, OnTimerStatusObtainedListener listener) {
        Card card = (Card) arguments.get(TimerActivity.EXTRA_CARD);
        TimerService.getInstance().finishTask();

        if(listener != null) {
            listener.onTimerStatusObtained(TimerService.getInstance().getTimerStatus(), card);
        }
    }
}
