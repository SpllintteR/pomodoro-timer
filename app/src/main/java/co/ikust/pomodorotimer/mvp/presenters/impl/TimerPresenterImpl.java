package co.ikust.pomodorotimer.mvp.presenters.impl;

import java.util.HashMap;

import javax.inject.Inject;

import co.ikust.pomodorotimer.mvp.interactors.TimerInteractor;
import co.ikust.pomodorotimer.mvp.presenters.TimerPresenter;
import co.ikust.pomodorotimer.mvp.views.TimerView;
import co.ikust.pomodorotimer.rest.models.Card;
import co.ikust.pomodorotimer.storage.models.TimerStatus;
import co.ikust.pomodorotimer.utils.Constants;

/**
 * Created by ivan on 16/04/15.
 */
public class TimerPresenterImpl implements TimerPresenter, TimerInteractor.OnTimerStatusObtainedListener {

    TimerView view;

    @Inject
    TimerInteractor interactor;

    public TimerPresenterImpl(TimerView view) {
        this.view = view;
    }

    @Override
    public void onTimerStatusObtained(TimerStatus timerStatus, Card card) {
        view.setTaskTitle(card.getName());
        view.setStatus(timerStatus.getState().name());

        switch(timerStatus.getState()) {
            case POMODORO_COUNTDOWN:
                view.setCurrentTime(Constants.POMODORO_TIME - card.getTrackedTime().getTime());
                break;

            case SHORT_BREAK_COUNTDOWN:
                view.setCurrentTime(Constants.SHORT_BREAK_TIME - card.getTrackedTime().getTime());
                break;

            case LONG_BREAK_COUNTDOWN:
                view.setCurrentTime(Constants.LONG_BREAK_TIME - card.getTrackedTime().getTime());
                break;

            default:
                view.setCurrentTime(0);
                break;
        }
    }

    @Override
    public void onViewCreated(HashMap<String, Object> arguments) {
        interactor.onViewCreated(arguments, this);
    }

    @Override
    public void onPauseTimer() {
        interactor.pause(this);
    }

    @Override
    public void onResumeTimer() {
        interactor.resume(this);
    }

    @Override
    public void onStopTimer() {
        interactor.stop(this);
    }

    @Override
    public void onTaskDone() {
        interactor.finishTask(this);
    }

    @Override
    public void onStartPomodoro() {
        interactor.startPomodoroCountDown(this);
    }

    @Override
    public void onStartShortBreak() {
        interactor.startShortBreakCountDown(this);
    }

    @Override
    public void onStartLongBreak() {
        interactor.startLongBreakCountDown(this);
    }
}
