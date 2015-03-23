package co.ikust.pomodorotimer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ivan on 23/03/15.
 */
public class TaskListFragment extends Fragment {

    private static final String LIST_ID_PARAM = "list_id";


    public static TaskListFragment newInstance(String listId) {
        TaskListFragment fragment = new TaskListFragment();

        Bundle params = new Bundle();
        params.putString(LIST_ID_PARAM, listId);

        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
