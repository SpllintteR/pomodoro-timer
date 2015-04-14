package co.ikust.pomodorotimer.rest.auth.impl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;

import co.ikust.pomodorotimer.rest.TrelloConstants;
import co.ikust.pomodorotimer.rest.auth.TokenManager;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static co.ikust.pomodorotimer.PomodoroTimerApplication.getInstance;

/**
 * Created by ivan on 23/03/15.
 */
public class TrelloTokenManager implements TokenManager {

    protected static void storeAccessToken(String token) {
        getDefaultSharedPreferences(getInstance())
                .edit()
                .putString(KEY_TOKEN, token)
                .commit();
    }

    protected static String loadToken() {
        return getDefaultSharedPreferences(getInstance()).getString(KEY_TOKEN, null);
    }

    @Override
    public void refreshToken(Context activityContext, final RefreshTokenCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
        builder.setTitle("Please input Trello token");

        // Set up the input
        final EditText input = new EditText(activityContext);
        input.setText("ad0fb4d348f8f8621e8f02d4dd38f454458591a743cf4b7b79dbb689cde3ab8a");
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                storeAccessToken(input.getText().toString());

                if(callback != null) {
                    callback.onComplete();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

                if(callback != null) {
                    callback.onCancel();
                }
            }
        });

        builder.show();
    }

    @Override
    public boolean hasToken() {
        return getDefaultSharedPreferences(getInstance()).contains(KEY_TOKEN);
    }

    @Override
    public String getToken() {
        return loadToken();
    }

    @Override
    public String getTokenSecret() {
        return TrelloConstants.APP_KEY;
    }
}
