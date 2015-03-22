package co.ikust.pomodorotimer.rest.oauth;

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
public class TrelloTokenManager implements TokenManager {

    protected final static String KEY_TOKEN = "token";

    protected final static String KEY_TOKEN_SECRET = "token_secret";

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

    protected static OAuthConsumer loadAccessToken() {
        OAuthConsumer consumer = createConsumer();
        consumer.setTokenWithSecret(
                getDefaultSharedPreferences(getInstance()).getString(KEY_TOKEN, null),
                getDefaultSharedPreferences(getInstance()).getString(KEY_TOKEN_SECRET, null)
        );

        return consumer;
    }

    @Override
    public void refreshToken(final RefreshTokenCallback callback) {
        final OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
                TrelloConstants.APP_KEY,
                TrelloConstants.APP_SECRET
        );

        TrelloDialog trelloDialog = new TrelloDialog(getInstance(), consumer, "", new Trello.DialogListener() {
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
    public OAuthConsumer getToken() {
        return loadAccessToken();
    }
}
