package co.ikust.pomodorotimer;

import co.ikust.pomodorotimer.rest.RestService;
import co.ikust.pomodorotimer.rest.TrelloRequestInterceptor;
import co.ikust.pomodorotimer.rest.auth.TokenManager;
import co.ikust.pomodorotimer.rest.auth.impl.TrelloTokenManager;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.getInstance;

/**
 * Dagger module that injects global application dependencies.
 */
@Module(
        injects = PomodoroTimerApplication.class
)
public class ApplicationModule {

    @Provides
    public RestService provideRestService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getInstance().getString(R.string.rest_api_endpoint))
                .setRequestInterceptor(new TrelloRequestInterceptor())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog(getInstance().getString(R.string.network_log_tag)))
                .build();

        return restAdapter.create(RestService.class);
    }

    @Provides
    public TokenManager provideTokenManager() {
        return new TrelloTokenManager();
    }
}
