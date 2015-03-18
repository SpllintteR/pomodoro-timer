/*
 * Copied from https://github.com/grantland/twitter-android-sdk
 * and modified to work with Trello by Ivan Kušt.
 */

package co.ikust.pomodorotimer.trello;

/**
 * Created by ivan on 18/03/15.
 */
public class Trello {

    public static final String TAG = "Trello";
    public static final boolean DEBUG = false;

    public static final String REQUEST_TOKEN = "https://trello.com/1/OAuthGetRequestToken";
    public static final String ACCESS_TOKEN = "https://trello.com/1/OAuthGetAccessToken";
    public static final String AUTHORIZE = "https://trello.com/1/OAuthAuthorizeToken";
    public static final String DENIED = "denied";
    public static final String CALLBACK_URI = "callback";

    public static final String EXTRA_ERROR = "error";
    public static final String EXTRA_CONSUMER = "consumer";
    public static final String EXTRA_AUTHORIZE_PARAMS = "params";
    public static final String EXTRA_ACCESS_KEY = "access_key";
    public static final String EXTRA_ACCESS_SECRET = "access_secret";

    // Used as default activityCode by authorize(). See authorize() below.
    public static final int DEFAULT_AUTH_ACTIVITY_CODE = 4242;




    /**
     * Callback interface for dialog requests.
     */
    public static interface DialogListener {

        /**
         * Called when a dialog completes.
         *
         * Executed by the thread that initiated the dialog.
         *
         * @param values
         *            Key-value string pairs extracted from the response.
         */
        public void onComplete(String accessKey, String accessSecret);

        /**
         * Called when a dialog has an error.
         *
         * Executed by the thread that initiated the dialog.
         */
        public void onError(TrelloError error);

        /**
         * Called when a dialog is canceled by the user.
         *
         * Executed by the thread that initiated the dialog.
         */
        public void onCancel();

    }
}
