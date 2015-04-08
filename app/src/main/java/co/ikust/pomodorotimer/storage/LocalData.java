package co.ikust.pomodorotimer.storage;


import co.ikust.pomodorotimer.rest.models.Board;
import co.ikust.pomodorotimer.rest.models.List;
import co.ikust.pomodorotimer.rest.models.Member;
import co.ikust.pomodorotimer.storage.models.TaskTime;

/**
 * Interface that defines access method to the data stored locally in the application.
 */
public interface LocalData {

    /**
     * Returns the list of {@link co.ikust.pomodorotimer.storage.models.TaskTime} objects containing
     * total time and Pomodoro count tracked for associated tasks.
     *
     * @return
     */
    java.util.List<TaskTime> getTrackedTaskTimes();

    /**
     * Updates the time tracked and Pomodoro count for the particular task.
     *
     * @param taskTime {@link TaskTime} object that represents time and Pomodoro count for a task
     */
    void updateTaskTime(TaskTime taskTime);

    void setMember(Member member);

    Member getMember();

    void setBoard(Board board);

    Board getBoard();

    void addList(List list);

    List getList(String listId);

    List getToDoList();

    List getDoingList();

    List getDoneList();

}
