package co.ikust.pomodorotimer.mvp.modules;

import co.ikust.pomodorotimer.activities.TimerActivity;
import co.ikust.pomodorotimer.mvp.interactors.TimerInteractor;
import co.ikust.pomodorotimer.mvp.interactors.impl.TimerInteractorImpl;
import co.ikust.pomodorotimer.mvp.presenters.TimerPresenter;
import co.ikust.pomodorotimer.mvp.presenters.impl.TimerPresenterImpl;
import co.ikust.pomodorotimer.mvp.views.TimerView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ivan on 16/04/15.
 */
@Module(
        injects = {
                TimerActivity.class,
                TimerPresenterImpl.class
        }
)
public class TimerModule {

    private TimerView timerView;

    public TimerModule(TimerView timerView) {
        this.timerView = timerView;
    }

    @Provides
    public TimerPresenter providePresenter() {
        return new TimerPresenterImpl(timerView);
    }

    @Provides
    public TimerInteractor provideInteractor() {
        return new TimerInteractorImpl();
    }

}
