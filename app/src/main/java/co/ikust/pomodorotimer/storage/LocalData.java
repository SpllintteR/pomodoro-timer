package co.ikust.pomodorotimer.storage;


import co.ikust.pomodorotimer.rest.models.Board;
import co.ikust.pomodorotimer.rest.models.List;
import co.ikust.pomodorotimer.rest.models.Member;
import co.ikust.pomodorotimer.storage.models.TimerStatus;

/**
 * Interface that defines access method to the data stored locally in the application.
 */
public interface LocalData {

    void addMember(Member member);

    Member getMember(String memberId);

    Member getMember();

    void addBoard(Board board);

    Board getBoard();

    Board getBoard(String boardId);

    void addList(List list);

    List getList(String listId);

    List getToDoList();

    List getDoingList();

    List getDoneList();

    TimerStatus getTimerStatus();

    void refreshTimerStatus(TimerStatus timerStatus);
}
