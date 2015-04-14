package co.ikust.pomodorotimer.preferences;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

import java.util.ArrayList;

import co.ikust.pomodorotimer.R;
import co.ikust.pomodorotimer.rest.models.Board;
import co.ikust.pomodorotimer.rest.models.Member;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.getLocalData;
import static co.ikust.pomodorotimer.PomodoroTimerApplication.getRestService;

/**
 * Created by ivan on 06/04/15.
 */
public class TrelloBoardPreference extends DialogPreference implements PreferenceManager.OnActivityDestroyListener {

    private Callback<Board> boardLoadCallback = new Callback<Board>() {
        @Override
        public void success(Board board, Response response) {
            if(dialog != null && dialog.isShowing()) {
                dialog.hide();
                dialog = null;
            }

            persistString(board.getId());
            getLocalData().addBoard(board);

            setSummary(board.getName());

            isDialogShown = false;
        }

        @Override
        public void failure(RetrofitError error) {
            dialog = new AlertDialog.Builder(getContext())
                    .setTitle(getContext().getString(R.string.error))
                    .setMessage(error.getMessage())
                    .show();

            if(dialog != null && dialog.isShowing()) {
                dialog.hide();
                dialog = null;
            }

            isDialogShown = false;
        }
    };

    private Dialog dialog;

    /**
     * Flag that notifies that preference was clicked and dialog is shown.
     */
    private boolean isDialogShown;

    public TrelloBoardPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        if(restorePersistedValue) {
            setSummary(getLocalData().getBoard().getName());
        } else {
            setSummary(getContext().getString(R.string.pref_description_board));
        }
    }

    private String[] boardsToStringArray(ArrayList<Board> boardList) {
        String[] boards = new String[boardList.size()];

        for(int i = 0; i < boardList.size(); i++) {
            boards[i] = boardList.get(i).getName();
        }

        return boards;
    }

    @Override
    protected void onClick() {
        if(!isDialogShown) {
            final Member member = getLocalData().getMember();

            dialog = new AlertDialog.Builder(getContext())
                    .setSingleChoiceItems(boardsToStringArray(member.getBoards()), 1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialog.dismiss();

                            dialog = new ProgressDialog(getContext());
                            dialog.show();

                            String boardId = member.getBoards().get(which).getId();
                            getRestService().getBoard(boardId, boardLoadCallback);
                        }
                    })
                    .show();

            isDialogShown = true;
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        //TODO store instance state
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        //TODO restore instance state
        super.onRestoreInstanceState(state);
    }

    @Override
    public void onActivityDestroy() {
        if(dialog == null || !dialog.isShowing()) {
            return;
        }

        dialog.dismiss();
    }
}
