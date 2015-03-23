package co.ikust.pomodorotimer.mvp.views;

import java.util.ArrayList;

import co.ikust.pomodorotimer.storage.models.TaskTime;

/**
 * Created by ivan on 23/03/15.
 */
public interface TaskListView {

    void addItems(ArrayList<TaskTime> items);

    void clear();

    void onItemClicked(TaskTime item);

    void showError(String message);

    void showLoading();

    void hideLoading();
}
