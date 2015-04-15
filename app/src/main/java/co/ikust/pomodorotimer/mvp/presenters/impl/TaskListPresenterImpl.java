package co.ikust.pomodorotimer.mvp.presenters.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import co.ikust.pomodorotimer.mvp.interactors.TaskListInteractor;
import co.ikust.pomodorotimer.mvp.presenters.TaskListPresenter;
import co.ikust.pomodorotimer.mvp.views.TaskListView;
import co.ikust.pomodorotimer.rest.models.Card;

/**
 * Created by ivan on 23/03/15.
 */
public class TaskListPresenterImpl implements TaskListPresenter, TaskListInteractor.OnTasksLoaded {

    @Inject
    TaskListInteractor interactor;

    TaskListView view;

    public TaskListPresenterImpl(TaskListView view) {
        this.view = view;
    }

    @Override
    public void onViewCreated(HashMap<String, Object> arguments) {
        view.showLoading();
        interactor.loadCachedTasks(arguments, this);
    }

    @Override
    public void onRefresh(HashMap<String, Object> arguments) {
        view.showLoading();
        interactor.refreshTasks(arguments, this);
    }

    @Override
    public void onItemClicked(Card task) {
        view.onItemClicked(task);
    }


    //region OnTasksLoaded implementation
    @Override
    public void onTasksLoaded(ArrayList<Card> tasks) {
        view.hideLoading();
        view.clear();
        view.addItems(tasks);
    }

    @Override
    public void onError(Throwable error) {
        view.hideLoading();
        view.showError(error.getMessage());
    }

    //endregion
}
