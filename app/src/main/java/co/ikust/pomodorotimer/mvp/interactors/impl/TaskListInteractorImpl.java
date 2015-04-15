package co.ikust.pomodorotimer.mvp.interactors.impl;

import java.util.HashMap;

import co.ikust.pomodorotimer.R;
import co.ikust.pomodorotimer.fragments.TaskListFragment;
import co.ikust.pomodorotimer.mvp.interactors.TaskListInteractor;
import co.ikust.pomodorotimer.rest.models.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.getInstance;
import static co.ikust.pomodorotimer.PomodoroTimerApplication.getLocalData;
import static co.ikust.pomodorotimer.PomodoroTimerApplication.getRestService;

/**
 * Created by ivan on 23/03/15.
 */
public class TaskListInteractorImpl implements TaskListInteractor {

    @Override
    public void loadCachedTasks(HashMap<String, Object> arguments, OnTasksLoaded listener) {
        String taskListId = (String) arguments.get(TaskListFragment.LIST_ID_PARAM);

        List taskList = getLocalData().getList(taskListId);

        if(listener != null) {
            if(taskList != null) {
                listener.onTasksLoaded(taskList.getCards());
            } else {
                listener.onError(new IllegalStateException(getInstance().getString(R.string.error_loading_task_list)));
            }
        }
    }

    @Override
    public void refreshTasks(HashMap<String, Object> arguments, final OnTasksLoaded listener) {
        String taskListId = (String) arguments.get(TaskListFragment.LIST_ID_PARAM);

        getRestService().getList(taskListId, new Callback<List>() {
            @Override
            public void success(List list, Response response) {
                //TODO getLocalData().updateList(list);

                if(listener != null) {
                    listener.onTasksLoaded(list.getCards());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if(listener != null) {
                    listener.onError(error);
                }
            }
        });
    }
}
