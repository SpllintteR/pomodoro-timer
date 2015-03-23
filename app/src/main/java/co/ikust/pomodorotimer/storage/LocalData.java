package co.ikust.pomodorotimer.storage;

import java.util.List;

import co.ikust.pomodorotimer.storage.models.TaskTime;

/**
 * Created by ivan on 23/03/15.
 */
public interface LocalData {

    /**
     * Returns the list of {@link co.ikust.pomodorotimer.storage.models.TaskTime} objects containing
     * total time and Pomodoro count tracked for associated tasks.
     *
     * @return
     */
    List<TaskTime> getTrackedTaskTimes();


}
