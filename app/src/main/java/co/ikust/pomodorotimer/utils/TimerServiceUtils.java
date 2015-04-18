package co.ikust.pomodorotimer.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import co.ikust.pomodorotimer.PomodoroTimerApplication;
import co.ikust.pomodorotimer.TimerService;

/**
 * Utility methods related to {@link TimerService}.
 */
public class TimerServiceUtils {

    public interface OnServiceStartedListener {
        void onServiceStarted();
    }

    public static void startTimerService(final OnServiceStartedListener listener) {
        if(TimerService.getInstance() == null) {
            waitForServiceToStart(new Runnable() {
                @Override
                public void run() {
                    if(listener != null) {
                        listener.onServiceStarted();
                    }
                }
            });

            Intent intent = new Intent(PomodoroTimerApplication.getInstance(), TimerService.class);
            PomodoroTimerApplication.getInstance().startService(intent);
        } else {
            if(listener != null) {
                listener.onServiceStarted();
            }
        }
    }

    /**
     * Executes the given <code>Runnable</code> once {@link TimerService}
     * is started. Method returns immediately. Start <code>Intent</code> must be sent to the service
     * after calling this method as it triggers the runnable execution (no matter if te service is
     * already running or not). Otherwise given runnable will never be run.
     *
     * @param executeWhenStarted Runnable to execute once service is started
     */
    private static void waitForServiceToStart(final Runnable executeWhenStarted) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(TimerService.ACTION_STARTED);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                executeWhenStarted.run();
                LocalBroadcastManager.getInstance(PomodoroTimerApplication.getInstance()).unregisterReceiver(this);
            }
        };

        LocalBroadcastManager.getInstance(PomodoroTimerApplication.getInstance()).registerReceiver(receiver, filter);
    }
}
