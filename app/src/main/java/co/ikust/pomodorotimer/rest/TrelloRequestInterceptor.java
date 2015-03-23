package co.ikust.pomodorotimer.rest;

import co.ikust.pomodorotimer.PomodoroTimerApplication;
import retrofit.RequestInterceptor;

/**
 * Created by ivan on 23/03/15.
 */
public class TrelloRequestInterceptor implements RequestInterceptor {

    private static final String KEY_PARAM = "key";

    private final static String TOKEN_PARAM = "token";

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam(KEY_PARAM, PomodoroTimerApplication.getAccessTokenSecret());
        request.addQueryParam(TOKEN_PARAM, PomodoroTimerApplication.getAccessToken());
    }
}
