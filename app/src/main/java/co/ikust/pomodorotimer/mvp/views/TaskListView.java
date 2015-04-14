package co.ikust.pomodorotimer.mvp.views;

import java.util.ArrayList;

import co.ikust.pomodorotimer.rest.models.Card;

/**
 * Created by ivan on 23/03/15.
 */
public interface TaskListView {

    void addItems(ArrayList<Card> items);

    void clear();

    void onItemClicked(Card item);

    void showError(String message);

    void showLoading();

    void hideLoading();
}
