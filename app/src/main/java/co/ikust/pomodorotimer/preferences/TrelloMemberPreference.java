package co.ikust.pomodorotimer.preferences;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Parcelable;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;

import co.ikust.pomodorotimer.R;
import co.ikust.pomodorotimer.rest.auth.TokenManager;
import co.ikust.pomodorotimer.rest.models.Member;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.getLocalData;
import static co.ikust.pomodorotimer.PomodoroTimerApplication.getRestService;
import static co.ikust.pomodorotimer.PomodoroTimerApplication.refreshAccessToken;

/**
 * Created by ivan on 06/04/15.
 */
public class TrelloMemberPreference extends Preference implements PreferenceManager.OnActivityDestroyListener {

    private TokenManager.RefreshTokenCallback refreshTokenCallback = new TokenManager.RefreshTokenCallback() {
        @Override
        public void onComplete() {
            dialog = new ProgressDialog(getContext());
            dialog.show();

            getRestService().getMember(memberLoadCallback);
        }

        @Override
        public void onError(Throwable error) {
            dialog = new AlertDialog.Builder(getContext())
                    .setTitle(getContext().getString(R.string.authorization_error))
                    .setMessage(error.getMessage())
                    .show();
            
            isDialogShown = false;
        }

        @Override
        public void onCancel() {
            isDialogShown = false;
        }
    };

    private Callback<Member> memberLoadCallback = new Callback<Member>() {
        @Override
        public void success(Member member, Response response) {
            if(dialog != null && dialog.isShowing()) {
                dialog.hide();
                dialog = null;
            }

            persistString(member.getId());
            getLocalData().addMember(member);

            setSummary(member.getFullName());

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

    /**
     * Currently shown dialog.
     */
    private Dialog dialog;

    /**
     * Flag that notifies that authorization dialog is shown.
     */
    private boolean isDialogShown;

    public TrelloMemberPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        isDialogShown = false;
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        if(restorePersistedValue) {
            setSummary(getLocalData().getMember().getFullName());
        } else {
            setSummary(getContext().getString(R.string.pref_description_member));
        }
    }

    @Override
    protected void onClick() {
        if(!isDialogShown) {
            refreshAccessToken(getContext(), refreshTokenCallback);

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
