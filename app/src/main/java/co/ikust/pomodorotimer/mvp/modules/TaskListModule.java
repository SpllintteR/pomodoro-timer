package co.ikust.pomodorotimer.mvp.modules;

import co.ikust.pomodorotimer.fragments.TaskListFragment;
import co.ikust.pomodorotimer.mvp.interactors.TaskListInteractor;
import co.ikust.pomodorotimer.mvp.interactors.impl.TaskListInteractorImpl;
import co.ikust.pomodorotimer.mvp.presenters.TaskListPresenter;
import co.ikust.pomodorotimer.mvp.presenters.impl.TaskListPresenterImpl;
import co.ikust.pomodorotimer.mvp.views.TaskListView;
import dagger.Module;
import dagger.Provides;

/**
 * Module that injects {@link co.ikust.pomodorotimer.fragments.TaskListFragment} dependencies.
 */
@Module(
    injects = {
            TaskListFragment.class,
            TaskListPresenterImpl.class
    }
)
public class TaskListModule {

    private TaskListView view;

    public TaskListModule(TaskListView view) {
        this.view = view;
    }

    @Provides
    public TaskListPresenter providePresenter() {
        return new TaskListPresenterImpl(view);
    }

    @Provides
    public TaskListInteractor provideInteractor() {
        return new TaskListInteractorImpl();
    }

}
