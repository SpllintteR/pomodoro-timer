/*
 * Copied from https://github.com/grantland/twitter-android-sdk
 * and modified to work with Trello by Ivan Kušt.
 */

package co.ikust.pomodorotimer.trello;

public class TrelloError extends Throwable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

//    private int mErrorCode;
//    private String mFailingUrl;

    public TrelloError(String message) {
        super(message);
    }

    public TrelloError(String message, int errorCode, String failingUrl) {
        super(message);
//        mErrorCode = errorCode;
//        mFailingUrl = failingUrl;
    }

//    public int getErrorCode() {
//        return mErrorCode;
//    }
}