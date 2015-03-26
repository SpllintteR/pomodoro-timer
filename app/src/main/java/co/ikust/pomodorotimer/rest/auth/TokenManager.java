package co.ikust.pomodorotimer.rest.auth;

import android.content.Context;

/**
 * Created by ivan on 20/03/15.
 */
public interface TokenManager {

    final static String KEY_TOKEN = "token";

    final static String KEY_TOKEN_SECRET = "token_secret";

    public interface RefreshTokenCallback {
        /**
         * Called when the OAuth token refresh completes successfully.
         */
        public void onComplete();

        /**
         * Called when a dialog has an onError.
         *
         * Executed by the thread that initiated the dialog.
         */
        public void onError(Throwable error);

        /**
         * Called when a dialog is canceled by the user.
         *
         * Executed by the thread that initiated the dialog.
         */
        public void onCancel();
    }

    void refreshToken(Context activityContext, RefreshTokenCallback callback);

    boolean hasToken();

    String getToken();

    String getTokenSecret();


}
