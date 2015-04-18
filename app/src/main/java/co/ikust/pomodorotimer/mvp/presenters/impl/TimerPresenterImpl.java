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
        view.setPomodoroCount(card.getTrackedTime().getPomodoroCount());
        view.setTotalTime(card.getTrackedTime().getTime());

        switch(timerStatus.getState()) {
            case POMODORO_COUNTDOWN:
                view.setCurrentTime(Constants.POMODORO_TIME - card.getTrackedTime().getTime());
                break;

            case POMODORO_FINISHED:
                view.setCurrentTime(0);
                view.showPomodoroDone();
                break;
            case SHORT_BREAK_COUNTDOWN:
                view.setCurrentTime(Constants.SHORT_BREAK_TIME - card.getTrackedTime().getTime());
                break;

            case LONG_BREAK_COUNTDOWN:
                view.setCurrentTime(Constants.LONG_BREAK_TIME - card.getTrackedTime().getTime());
                break;

            case BREAK_FINISHED:
                view.setCurrentTime(0);
                view.showBreakDone();
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
    public void onEnteredForeground(HashMap<String, Object> arguments) {
        interactor.showNotifications(false);
        interactor.registerTickListener(arguments, this);
    }

    @Override
    public void onEnteredBackground() {
        interactor.showNotifications(true);
        interactor.unregisterTickListener();
    }

    @Override
    public void onStartPomodoro(HashMap<String, Object> arguments) {
        interactor.startPomodoroCountDown(arguments, this);
    }

    @Override
    public void onStartShortBreak(HashMap<String, Object> arguments) {
        interactor.startShortBreakCountDown(arguments, this);
    }

    @Override
    public void onStartLongBreak(HashMap<String, Object> arguments) {
        interactor.startLongBreakCountDown(arguments, this);
    }

    @Override
    public void onPauseTimer(HashMap<String, Object> arguments) {
        interactor.pause(arguments, this);
    }

    @Override
    public void onResumeTimer(HashMap<String, Object> arguments) {
        interactor.resume(arguments, this);
    }

    @Override
    public void onStopTimer(HashMap<String, Object> arguments) {
        interactor.stop(arguments, this);
    }

    @Override
    public void onTaskDone(HashMap<String, Object> arguments) {
        interactor.finishTask(arguments, this);
    }


}
