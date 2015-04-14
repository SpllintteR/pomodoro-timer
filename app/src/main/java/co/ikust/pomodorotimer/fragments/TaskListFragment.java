package co.ikust.pomodorotimer.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;
import co.ikust.pomodorotimer.R;
import co.ikust.pomodorotimer.adapters.TaskAdapter;
import co.ikust.pomodorotimer.mvp.modules.TaskListModule;
import co.ikust.pomodorotimer.mvp.presenters.TaskListPresenter;
import co.ikust.pomodorotimer.mvp.views.TaskListView;
import co.ikust.pomodorotimer.rest.models.Card;
import dagger.ObjectGraph;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.getInstance;
import static co.ikust.pomodorotimer.utils.ActivityUtils.bundleToHashMap;


/**
 * Created by ivan on 23/03/15.
 */
public class TaskListFragment extends Fragment implements TaskListView {

    public interface TaskListCallbacks {
        void showLoading(boolean loading);

        void startTimer(Card task);
    }

    public static final String LIST_ID_PARAM = "list_id";

    @InjectView(R.id.lvTasks)
    ListView listView;

    @Inject
    TaskListPresenter presenter;

    private TaskAdapter adapter;

    private View rootView;

    private TaskListCallbacks callbacks;

    public static TaskListFragment newInstance(String listId) {
        TaskListFragment fragment = new TaskListFragment();

        Bundle params = new Bundle();
        params.putString(LIST_ID_PARAM, listId);

        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            callbacks = (TaskListCallbacks) activity;
        } catch(ClassCastException e) {
            throw new ClassCastException("Activity must implement TaskListCallbacks.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_task_list, container, false);

        ButterKnife.inject(this, rootView);

        adapter = new TaskAdapter(getActivity(), new ArrayList<Card>());
        listView.setAdapter(adapter);

        injectLocalGraph();
        presenter.onViewCreated(bundleToHashMap(getArguments()));

        return rootView;
    }

    protected void injectLocalGraph() {
        ObjectGraph localGraph = getInstance().getGlobalGraph()
                .plus(new TaskListModule(this));

        localGraph.inject(this);
        localGraph.inject(presenter);
    }

    @OnItemClick(R.id.lvTasks)
    public void onItemClick(int position) {
        presenter.onItemClicked(adapter.getItem(position));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.reset(this);
        rootView = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        callbacks = null;
    }

    //region TaskListView implementation
    @Override
    public void addItems(ArrayList<Card> items) {
        adapter.addAll(items);
    }

    @Override
    public void clear() {
        adapter.clear();
    }

    @Override
    public void onItemClicked(Card task) {
        callbacks.startTimer(task);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showLoading() {
        callbacks.showLoading(true);
    }

    @Override
    public void hideLoading() {
        callbacks.showLoading(false);
    }
    //endregion
}
