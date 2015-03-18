package co.ikust.pomodorotimer;

import android.app.Application;

import javax.inject.Inject;

import co.ikust.pomodorotimer.rest.RestService;
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

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        globalGraph = ObjectGraph.create(new ApplicationModule());
        globalGraph.inject(this);
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


}
