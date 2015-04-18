package co.ikust.pomodorotimer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import co.ikust.pomodorotimer.rest.models.Card;
import co.ikust.pomodorotimer.rest.models.List;
import co.ikust.pomodorotimer.storage.models.TimerStatus;
import co.ikust.pomodorotimer.utils.Constants;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.getLocalData;

/**
 * Service that performs task time tracking. Once it is started it can be
 * used as singleton by calling {@link #getInstance()}.
 */
public class TimerService extends Service {

    public interface TimerServiceListener {
        void onTimerStatusChanged(TimerStatus status);
    }

    public static final String ACTION_STARTED = "start";

    private static TimerService instance;

    private ScheduledThreadPoolExecutor executor;

    private ScheduledFuture<?> runningTimer;

    private TimerServiceListener listener;

    private boolean showNotifications;

    @Override
    public IBinder onBind(Intent intent) {
        //We won't be binding this service, we'll just use it as singleton.
        //(We can do that since it will be started in the same process as the app.)
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        instance = this;

        //Create new executor with only one thread that will handle timer events.
        executor = new ScheduledThreadPoolExecutor(1);

        Intent callbackIntent = new Intent(ACTION_STARTED);
        LocalBroadcastManager.getInstance(this).sendBroadcast(callbackIntent);

        return START_STICKY;
    }



    /**
     * Returns the instance of this service once started or <code>null</code>.
     *
     * @return service instance or null
     */
    public static TimerService getInstance() {
        return instance;
    }

    public TimerStatus getTimerStatus() {
        return getLocalData().getTimerStatus();
    }

    public void registerListener(TimerServiceListener listener) {
        this.listener = listener;
    }

    public void unregisterListener() {
        this.listener = null;
    }

    /**
     * Turns on showing countdown in notifications.
     */
    public void showNotifications(boolean show) {
        this.showNotifications = show;
    }

    private void stopTaskIfFinished(TimerStatus timerStatus, long taskLength, TimerStatus.State newState) {
        if(timerStatus.getTime() >= taskLength) {
            runningTimer.cancel(false);
            runningTimer = null;
        }

        timerStatus.setState(newState);
    }

    private Runnable pomodoroCountdownTask = new Runnable() {
        @Override
        public void run() {
            TimerStatus timerStatus = getLocalData().getTimerStatus();
            timerStatus.addTime(Constants.TIMER_TICK_INTERVAL);

            switch(timerStatus.getState()) {
                case POMODORO_COUNTDOWN:
                    stopTaskIfFinished(timerStatus, Constants.POMODORO_TIME, TimerStatus.State.POMODORO_FINISHED);
                    break;

                case SHORT_BREAK_COUNTDOWN:
                    stopTaskIfFinished(timerStatus, Constants.SHORT_BREAK_TIME, TimerStatus.State.BREAK_FINISHED);
                    break;

                case LONG_BREAK_COUNTDOWN:
                    stopTaskIfFinished(timerStatus, Constants.LONG_BREAK_TIME, TimerStatus.State.BREAK_FINISHED);
                    break;
            }

            //Refresh timer status.
            getLocalData().refreshTimerStatus(timerStatus);

        }
    };

    /**
     * Starts count down timer.
     *
     * @param card {@link Card} model representing task for which time is tracked
     * @param timerState state to put the timer in when countdown is started
     */
    public void startCountDown(Card card, TimerStatus.State timerState) {
        //Refresh timer status.
        TimerStatus timerStatus = getLocalData().getTimerStatus();

        if(timerStatus.getState() != TimerStatus.State.DONE) {
            commitTimeToTask();
        }

        timerStatus.setPaused(false);
        timerStatus.setState(timerState);
        timerStatus.setTaskId(card.getId());
        timerStatus.setListId(card.getListId());
        timerStatus.resetTime();

        getLocalData().refreshTimerStatus(timerStatus);

        //Schedule at fixed rate for precision (counts intervals from start of the execution).
        runningTimer = executor.scheduleAtFixedRate(pomodoroCountdownTask, 0, Constants.TIMER_TICK_INTERVAL, TimeUnit.MILLISECONDS);
    }

    /**
     * Pauses currently running timer. Returns <code>true</code> if timer
     * was currently running and was successfully paused, <code>false</code>
     * otherwise.
     *
     * @return true if timer was paused successfully
     */
    public boolean pause() {
        if (runningTimer == null) {
            return false;
        }

        TimerStatus timerStatus = getLocalData().getTimerStatus();

        //Coding defensively for consistency.
        if (timerStatus.getState() == TimerStatus.State.POMODORO_FINISHED ||
                timerStatus.getState() == TimerStatus.State.BREAK_FINISHED ||
                timerStatus.getState() == TimerStatus.State.DONE) {
            runningTimer = null; //task should not be running so clean it up
            return false;
        }

        runningTimer.cancel(false);
        runningTimer = null;

        //Update timer status.
        timerStatus.setPaused(true);
        getLocalData().refreshTimerStatus(timerStatus);

        return true;
    }

    /**
     * Resumes the timer. Returns <code>true</code> if timer
     * was currently paused and was successfully resumed, <code>false</code>
     * otherwise.
     *
     * @return true if timer was resumed successfully
     */
    public boolean resume() {
        TimerStatus timerStatus = getLocalData().getTimerStatus();

        //Check if we can resume currently.
        if (timerStatus.getState() == TimerStatus.State.POMODORO_FINISHED ||
                timerStatus.getState() == TimerStatus.State.BREAK_FINISHED ||
                timerStatus.getState() == TimerStatus.State.DONE) {
            return false;
        }

        //Restore the state of the timer.
        timerStatus.setPaused(false);
        getLocalData().refreshTimerStatus(timerStatus);

        //Schedule at fixed rate for precision (counts intervals from start of the execution).
        runningTimer = executor.scheduleAtFixedRate(pomodoroCountdownTask, 0, Constants.POMODORO_TIME, TimeUnit.MILLISECONDS);

        return true;
    }

    /**
     * Commits tracked time to task and increases pomodoro count if a
     * pomodoro was compleated.
     */
    private void commitTimeToTask() {
        TimerStatus timerStatus = getLocalData().getTimerStatus();
        List list = getLocalData().getList(timerStatus.getListId());
        Card card = list.getCardWithId(timerStatus.getTaskId());

        card.getTrackedTime().appendTime(timerStatus.getTime());

        if(timerStatus.getState() == TimerStatus.State.POMODORO_FINISHED) {
            card.getTrackedTime().increasePomodoroCount();
        }

        getLocalData().addList(list); //Overwrite list data with updated data.
    }

    /**
     * Stops the timer and commits the elapsed time to the task. Returns
     * <code>true</code> if the timer was currently running and was successfully
     * paused, <code>false</code> otherwise.
     *
     * @return true if the timer was stopped successfully
     */
    public boolean stop() {
        if (runningTimer == null) {
            return false;
        }

        TimerStatus timerStatus = getLocalData().getTimerStatus();

        //Coding defensively for consistency.
        if (timerStatus.getState() == TimerStatus.State.POMODORO_FINISHED ||
                timerStatus.getState() == TimerStatus.State.BREAK_FINISHED ||
                timerStatus.getState() == TimerStatus.State.DONE) {
            runningTimer = null; //task should not be running so clean it up
            return false;
        }

        runningTimer.cancel(false);
        runningTimer = null;

        finishTask();
        return true;
    }

    /**
     * Notifies that the task is done and commits all
     */
    public void finishTask() {
        commitTimeToTask();

        TimerStatus timerStatus = getLocalData().getTimerStatus();

        timerStatus.setPaused(false);
        timerStatus.setTaskId(null);
        timerStatus.setListId(null);
        timerStatus.setState(TimerStatus.State.DONE);
        getLocalData().refreshTimerStatus(timerStatus);
    }
}
