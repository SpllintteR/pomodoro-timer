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
import co.ikust.pomodorotimer.rest.models.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.getLocalData;
import static co.ikust.pomodorotimer.PomodoroTimerApplication.getRestService;

/**
 * Created by ivan on 06/04/15.
 */
public class TrelloListPreference extends DialogPreference implements PreferenceManager.OnActivityDestroyListener {

    private Callback<List> listLoadCallback = new Callback<List>() {
        @Override
        public void success(List list, Response response) {
            if(dialog != null && dialog.isShowing()) {
                dialog.hide();
                dialog = null;
            }

            persistString(list.getId());
            getLocalData().addList(list);

            setSummary(list.getName());

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
     * Flag that notifies taht preference was clicked and dialog is shown.
     */
    private boolean isDialogShown;

    public TrelloListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        super.onSetInitialValue(restorePersistedValue, defaultValue);

        if(restorePersistedValue) {
            List list = getLocalData().getList(getPersistedString(null));

            if(list != null) {
                setSummary(getLocalData().getList(getPersistedString(null)).getName());
            }
        } else {

        }
    }

    private String[] listsToStringArray(ArrayList<List> listOfLists) {
        String[] lists = new String[listOfLists.size()];

        for(int i = 0; i < listOfLists.size(); i++) {
            lists[i] = listOfLists.get(i).getName();
        }

        return lists;
    }

    @Override
    protected void onClick() {
        if(!isDialogShown) {
            final Board board = getLocalData().getBoard();

            dialog = new AlertDialog.Builder(getContext())
                    .setSingleChoiceItems(listsToStringArray(board.getLists()), -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialog.dismiss();

                            dialog = new ProgressDialog(getContext());
                            dialog.show();

                            String listId = board.getLists().get(which).getId();
                            getRestService().getList(listId, listLoadCallback);
                        }
                    })
                    .show();

            isDialogShown = true;
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
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
