package co.ikust.pomodorotimer;

import android.app.Application;

import javax.inject.Inject;

import co.ikust.pomodorotimer.rest.RestService;
import co.ikust.pomodorotimer.rest.oauth.TokenManager;
import dagger.ObjectGraph;

/**
 * Pomodoro Timer Application.
 */
public class PomodoroTimerApplication extends Application {

    /**
     * One and only instance of the application.
     */
    private static PomodoroTimerApplication instance;

    /**
     * Dagger ObjectGraph global for the whole application.
     */
    private ObjectGraph globalGraph;

    @Inject
    RestService apiService;

    @Inject
    TokenManager tokenManager;

    /**
     * Injects application dependencies and constructs global ObjectGraph.
     */
    private void injectDependencies() {
        globalGraph = ObjectGraph.create(new ApplicationModule());
        globalGraph.inject(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        injectDependencies();
    }

    /**
     * Returns global Dagger ObjectGraph.
     *
     * @return global ObjectGraph for the application
     */
    public ObjectGraph getGlobalGraph() {
        return globalGraph;
    }

    public static PomodoroTimerApplication getInstance() {
        return instance;
    }

    /**
     * Returns the implementation of {@link co.ikust.pomodorotimer.rest.RestService}.
     *
     * @return REST API service for the application
     */
    public static RestService getRestService() {
        return instance.apiService;
    }

    /**
     * Returns true if there is a stored OAuth access token.
     *
     * @return true if there is a stored OAuth token
     */
    public static boolean hasAccessToken() {
        return instance.tokenManager.hasToken();
    }

    /**
     * Updates the OAuth token. Notifies success or failure through callback.
     * RestService is rebuilt after successfull token refresh.
     */
    public static void refreshAccessToken(final TokenManager.RefreshTokenCallback callback) { //TODO replace with token callback
        instance.tokenManager.refreshToken(new TokenManager.RefreshTokenCallback() {
            @Override
            public void onComplete() {
                //Re-inject dependencies to trigger constructing new RestService which uses
                //new OAuth access token.
                instance.injectDependencies();

                if (callback != null) {
                    callback.onComplete();
                }
            }

            @Override
            public void onError(Throwable error) {
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

    }
}
