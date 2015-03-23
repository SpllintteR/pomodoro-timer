package co.ikust.pomodorotimer.utils;

import java.util.concurrent.TimeUnit;

import co.ikust.pomodorotimer.R;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.getInstance;

/**
 * Various formatting utility methods.
 */
public class FormatUtils {


    /**
     * Formats the given time in milliseconds to a hh:mm:ss string. Taken from StackOverflow
     * http://stackoverflow.com/a/9027379/4183841.
     *
     * @param millis time in milliseconds
     * @return String representing time in hh:mm:ss format
     */
    public static String formatTime(long millis) {
        return String.format(getInstance().getString(R.string.time_format), TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
    }

}
