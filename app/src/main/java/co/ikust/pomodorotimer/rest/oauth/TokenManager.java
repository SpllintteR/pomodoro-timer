package co.ikust.pomodorotimer.rest.oauth;

import oauth.signpost.OAuthConsumer;

/**
 * Created by ivan on 20/03/15.
 */
public interface TokenManager {

    public interface RefreshTokenCallback {
        /**
         * Called when the OAuth token refresh completes successfully.
         */
        public void onComplete();

        /**
         * Called when a dialog has an error.
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

    void refreshToken(RefreshTokenCallback callback);

    boolean hasToken();

    OAuthConsumer getToken();
}
