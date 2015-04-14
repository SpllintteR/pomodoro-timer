package co.ikust.pomodorotimer.mvp.interactors.impl;

import java.util.HashMap;

import co.ikust.pomodorotimer.fragments.TaskListFragment;
import co.ikust.pomodorotimer.mvp.interactors.TaskListInteractor;

/**
 * Created by ivan on 23/03/15.
 */
public class TaskListInteractorImpl implements TaskListInteractor {

    @Override
    public void loadCachedTasks(HashMap<String, Object> arguments, OnTasksLoaded listener) {
        String taskListId = (String) arguments.get(TaskListFragment.LIST_ID_PARAM);


    }

    @Override
    public void refreshTasks(HashMap<String, Object> arguments, OnTasksLoaded listener) {

    }
}
