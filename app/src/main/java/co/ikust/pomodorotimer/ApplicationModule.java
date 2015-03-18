package co.ikust.pomodorotimer;

import co.ikust.pomodorotimer.rest.RestService;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

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
                .build();

        return restAdapter.create(RestService.class);
    }
}
