package co.ikust.pomodorotimer.utils;

import co.ikust.pomodorotimer.rest.models.Card;
import co.ikust.pomodorotimer.rest.models.List;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.getLocalData;

/**
 * Created by ivan on 18/04/15.
 */
public class StorageUtils {

    public interface OnSyncCompleteListener {
        void onSyncComplete();
    }

    public void syncData(OnSyncCompleteListener listener) {
        //TODO fetch new data for lists
    }

    /**
     * Moved the given card to the Trello list associated with 'Done' tasks.
     *
     * @param card
     */
    public void moveToDoingList(Card card) {
        List toDoList = getLocalData().getToDoList();
        List doingList = getLocalData().getDoingList();

        toDoList.removeCard(card);
        doingList.addCard(card);

        getLocalData().addList(toDoList);
        getLocalData().addList(doingList);
    }

    public void moveToDoneList(Card card) {
        List doingList = getLocalData().getDoingList();
        List doneList = getLocalData().getDoneList();

        doingList.removeCard(card);
        doneList.addCard(card);

        getLocalData().addList(doingList);
        getLocalData().addList(doneList);
    }
}
