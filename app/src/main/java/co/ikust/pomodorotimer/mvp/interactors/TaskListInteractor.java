package co.ikust.pomodorotimer.mvp.interactors;

import java.util.ArrayList;
import java.util.HashMap;

import co.ikust.pomodorotimer.storage.models.TaskTime;

/**
 * Created by ivan on 23/03/15.
 */
public interface TaskListInteractor {

    public interface OnTasksLoaded {
        void onTasksLoaded(ArrayList<TaskTime> tasks);

        void onError(Throwable error);
    }

    void loadCachedTasks(HashMap<String, Object> arguments, OnTasksLoaded listener);

    void refreshTasks(HashMap<String, Object> arguments, OnTasksLoaded listener);
}
