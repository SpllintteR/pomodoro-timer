package co.ikust.pomodorotimer.storage.impl;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.List;

import co.ikust.pomodorotimer.rest.models.Board;
import co.ikust.pomodorotimer.rest.models.Member;
import co.ikust.pomodorotimer.storage.LocalData;
import co.ikust.pomodorotimer.storage.models.TaskTime;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static co.ikust.pomodorotimer.PomodoroTimerApplication.getInstance;

/**
 * Implementation that stores local application data in {@link android.content.SharedPreferences}.
 */
public class SharedPreferencesData implements LocalData {

    private static final String KEY_TASK_TIMES = "task_times";

    private static final String KEY_MEMBER = "member";

    private static final String KEY_BOARD = "board";

    private static final String KEY_TODO_LIST = "todo_list";

    private static final String KEY_DOING_LIST = "doing_list";

    private static final String KEY_DONE_LIST = "done_list";

    /**
     * Key format for keys that are used to cache Trello lists.
     */
    private static final String LIST_KEY_FORMAT = "list_%s";

    private static final String BOARD_KEY_FORMAT = "board_%s";

    /**
     * GSON instance used for serializing and deserializing objects to and from JSON strings.
     */
    private static Gson gson = new Gson();

    private static String read(String key, String defaultValue) {
        return getDefaultSharedPreferences(getInstance()).getString(key, defaultValue);
    }

    private static void write(String key, String value) {
        getDefaultSharedPreferences(getInstance())
                .edit()
                .putString(key, value)
                .apply();
    }

    @Override
    public List<TaskTime> getTrackedTaskTimes() {
        return null;
    }

    @Override
    public void updateTaskTime(TaskTime taskTime) {

    }

    @Override
    public void setMember(Member member) {
        Member localMember = new Member(member.getId(), member.getFullName());
        write(KEY_MEMBER, gson.toJson(localMember));
    }

    @Override
    public Member getMember() {
        try {
            return gson.fromJson(read(KEY_MEMBER, ""), Member.class);
        } catch (JsonSyntaxException e) {
            return null;
        }
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
