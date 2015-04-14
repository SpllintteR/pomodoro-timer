package co.ikust.pomodorotimer.mvp.presenters;

import java.util.HashMap;

import co.ikust.pomodorotimer.rest.models.Card;

/**
 * Created by ivan on 23/03/15.
 */
public interface TaskListPresenter {

    void onViewCreated(HashMap<String, Object> arguments);

    void onRefresh(HashMap<String, Object> arguments);

    /**
     * Called when a item representing account in the AccountsView is clicked.
     *
     * @param task
     */
    void onItemClicked(Card task);

}
