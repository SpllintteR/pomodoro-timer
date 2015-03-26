package co.ikust.pomodorotimer.storage.impl;

import java.util.List;

import co.ikust.pomodorotimer.rest.models.Board;
import co.ikust.pomodorotimer.rest.models.Member;
import co.ikust.pomodorotimer.storage.LocalData;
import co.ikust.pomodorotimer.storage.models.TaskTime;

/**
 * Implementation that stores local application data in {@link android.content.SharedPreferences}.
 */
public class SharedPreferencesData implements LocalData {

    @Override
    public List<TaskTime> getTrackedTaskTimes() {
        return null;
    }

    @Override
    public void updateTaskTime(TaskTime taskTime) {

    }

    @Override
    public void setMember(Member member) {

    }

    @Override
    public Member getMember() {
        return null;
    }

    @Override
    public void setBoard(Board board) {

    }

    @Override
    public Board getBoard() {
        return null;
    }

    @Override
    public void setToDoList(List list) {

    }

    @Override
    public List getToDoList() {
        return null;
    }

    @Override
    public void setDoingList(List list) {

    }

    @Override
    public List getDoingList() {
        return null;
    }

    @Override
    public void setDoneList(List list) {

    }

    @Override
    public List getDoneList() {
        return null;
    }
}
