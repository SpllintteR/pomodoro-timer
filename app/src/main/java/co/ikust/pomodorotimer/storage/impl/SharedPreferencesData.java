package co.ikust.pomodorotimer.storage.impl;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import android.support.annotation.NonNull;

import co.ikust.pomodorotimer.R;
import co.ikust.pomodorotimer.rest.models.Board;
import co.ikust.pomodorotimer.rest.models.List;
import co.ikust.pomodorotimer.rest.models.Member;
import co.ikust.pomodorotimer.storage.LocalData;
import co.ikust.pomodorotimer.storage.models.TimerStatus;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static co.ikust.pomodorotimer.PomodoroTimerApplication.getInstance;

/**
 * Implementation that stores local application data in {@link android.content.SharedPreferences}.
 */
public class SharedPreferencesData implements LocalData {

    /**
     * SharedPreferences key that holds the id of the linked member.
     */
    private static final String KEY_MEMBER = getInstance().getString(R.string.pref_member);

    /**
     * SharedPreferences key that holds the id of the selected board.
     */
    private static final String KEY_BOARD = getInstance().getString(R.string.pref_board);

    /**
     * SharedPreferences key that holds the id of the to-do list.
     */
    private static final String KEY_TODO_LIST = getInstance().getString(R.string.pref_todo_list);

    /**
     * SharedPreferences key that holds the id of the doing list.
     */
    private static final String KEY_DOING_LIST = getInstance().getString(R.string.pref_doing_list);

    /**
     * SharedPreferences key that holds the id of the done list.
     */
    private static final String KEY_DONE_LIST = getInstance().getString(R.string.pref_done_list);

    /**
     * SharedPreferences key that holds the Timer status model.
     */
    private static final String KEY_TIMER_STATUS = "timer_status";

    /**
     * Key format for keys that are used to cache Trello members.
     */
    private static final String MEMBER_KEY_FORMAT = "member_%s";

    /**
     * Key format for keys that are used to cache Trello boards.
     */
    private static final String BOARD_KEY_FORMAT = "board_%s";

    /**
     * Key format for keys that are used to cache Trello lists.
     */
    private static final String LIST_KEY_FORMAT = "list_%s";



    /**
     * GSON instance used for serializing and deserializing objects to and from JSON strings.
     */
    private static Gson gson = new Gson();

    private static String read(String key, String defaultValue) {
        return getDefaultSharedPreferences(getInstance()).getString(key, defaultValue);
    }

    protected <T> T readModel(@NonNull String keyFormat, String id, @NonNull Class<T> modelClass) {
        if(id == null) {
            return null;
        }

        try {
            return gson.fromJson(read(String.format(keyFormat, id), ""), modelClass);
        } catch(JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void write(String key, String value) {
        getDefaultSharedPreferences(getInstance())
                .edit()
                .putString(key, value)
                .apply();
    }

    @Override
    public void addMember(@NonNull Member member) {
        write(String.format(MEMBER_KEY_FORMAT, member.getId()), gson.toJson(member));
    }

    @Override
    public Member getMember(@NonNull String memberId) {
        return null;
    }

    @Override
    public Member getMember() {
        String linkedMemberId = read(KEY_MEMBER, null);

        if(linkedMemberId == null) {
            return null;
        }

        try {
            return gson.fromJson(read(String.format(MEMBER_KEY_FORMAT, linkedMemberId), ""), Member.class);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    @Override
    public void addBoard(@NonNull Board board) {
        write(String.format(BOARD_KEY_FORMAT, board.getId()), gson.toJson(board));
    }

    @Override
    public Board getBoard(@NonNull String boardId) {
        return readModel(BOARD_KEY_FORMAT, boardId, Board.class);
    }

    @Override
    public Board getBoard() {
        String selectedBoardId = read(KEY_BOARD, null);
        return getBoard(selectedBoardId);
    }

    @Override
    public void addList(@NonNull List list) {
        write(String.format(LIST_KEY_FORMAT, list.getId()), gson.toJson(list));
    }

    @Override
    public List getList(String listId) {
        return readModel(LIST_KEY_FORMAT, listId, List.class);
    }

    @Override
    public List getToDoList() {
        String toDoListId = read(KEY_TODO_LIST, null);
        return readModel(LIST_KEY_FORMAT, toDoListId, List.class);
    }

    @Override
    public List getDoingList() {
        String doingListId = read(KEY_DOING_LIST, null);
        return readModel(LIST_KEY_FORMAT, doingListId, List.class);
    }

    @Override
    public List getDoneList() {
        String doneListId = read(KEY_DONE_LIST, null);
        return readModel(LIST_KEY_FORMAT, doneListId, List.class);
    }

    @Override
    public TimerStatus getTimerStatus() {
        TimerStatus timerStatus = readModel(KEY_TIMER_STATUS, null, TimerStatus.class);

        if(timerStatus == null) {
            timerStatus = new TimerStatus();
        }

        return timerStatus;
    }

    @Override
    public void refreshTimerStatus(TimerStatus timerStatus) {
        write(KEY_TIMER_STATUS, gson.toJson(timerStatus));
    }
}
