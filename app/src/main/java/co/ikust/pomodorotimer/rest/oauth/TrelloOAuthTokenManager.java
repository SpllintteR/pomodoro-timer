package co.ikust.pomodorotimer.rest.oauth;

import android.content.Context;

import co.ikust.pomodorotimer.rest.TrelloConstants;
import co.ikust.pomodorotimer.trello.Trello;
import co.ikust.pomodorotimer.trello.TrelloDialog;
import co.ikust.pomodorotimer.trello.TrelloError;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static co.ikust.pomodorotimer.PomodoroTimerApplication.getInstance;

/**
 * OAuth {@link co.ikust.pomodorotimer.rest.oauth.TokenManager} for Trello.
 * <p>
 * Manages refreshing and storing access token key and secret.
 */
public class TrelloOAuthTokenManager implements TokenManager {



    protected static OAuthConsumer createConsumer() {
        return new CommonsHttpOAuthConsumer(
                TrelloConstants.APP_KEY,
                TrelloConstants.APP_SECRET
        );
    }

    protected static void storeAccessToken(OAuthConsumer consumer) {
        getDefaultSharedPreferences(getInstance())
                .edit()
                .putString(KEY_TOKEN, consumer.getToken())
                .putString(KEY_TOKEN_SECRET, consumer.getTokenSecret())
                .commit();
    }

    protected static String loadToken() {
        return getDefaultSharedPreferences(getInstance()).getString(KEY_TOKEN, null);
    }

    protected static String loadTokenSecret() {
        return getDefaultSharedPreferences(getInstance()).getString(KEY_TOKEN_SECRET, null);
    }

    @Override
    public void refreshToken(Context activityContext, final RefreshTokenCallback callback) {
        final OAuthConsumer consumer = createConsumer();

        TrelloDialog trelloDialog = new TrelloDialog(activityContext, consumer, "?name=Pomodoro+Timer&expiration=never&scope=read,write,account", new Trello.DialogListener() {
            @Override
            public void onComplete(String accessKey, String accessSecret) {
                storeAccessToken(consumer); //Store new access token.

                if (callback != null) {
                    callback.onComplete();
                }
            }

            @Override
            public void onError(TrelloError error) {
                if (callback != null) {
                    callback.onError(error);
                }
            }

            @Override
            public void onCancel() {
                if (callback != null) {
                    callback.onCancel();
                }
            }
        });

        trelloDialog.show();
    }

    @Override
    public boolean hasToken() {
        return getDefaultSharedPreferences(getInstance()).contains(KEY_TOKEN) &&
                getDefaultSharedPreferences(getInstance()).contains(KEY_TOKEN_SECRET);
    }

    @Override
    public String getToken() {
        return loadToken();
    }

    @Override
    public String getTokenSecret() {
        return loadTokenSecret();
    }
}
