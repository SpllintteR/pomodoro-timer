package co.ikust.pomodorotimer.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.ikust.pomodorotimer.R;
import co.ikust.pomodorotimer.mvp.modules.TimerModule;
import co.ikust.pomodorotimer.mvp.presenters.TimerPresenter;
import co.ikust.pomodorotimer.mvp.views.TimerView;
import dagger.ObjectGraph;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.getInstance;
import static co.ikust.pomodorotimer.utils.ActivityUtils.bundleToHashMap;
import static co.ikust.pomodorotimer.utils.FormatUtils.formatTime;

public class TimerActivity extends ActionBarActivity implements TimerView {

    public static final String EXTRA_CARD = "card";

    @InjectView(R.id.tv_taskTitle)
    TextView taskTitleTextView;

    @InjectView(R.id.tv_status)
    TextView statusTextView;

    @InjectView(R.id.tv_time)
    TextView timeTextView;

    @InjectView(R.id.tv_pomodoros)
    TextView pomodorosTextView;

    @InjectView(R.id.tv_total_time)
    TextView totalTimeTextView;

    @InjectView(R.id.btn_pause)
    Button pauseButton;

    @InjectView(R.id.btn_stop)
    Button stopButton;

    @Inject
    TimerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        ButterKnife.inject(this);
        injectDependencies();

        presenter.onViewCreated(bundleToHashMap(getIntent().getExtras()));
    }

    private void injectDependencies() {
        ObjectGraph localGraph = getInstance().getGlobalGraph()
                .plus(new TimerModule(this));

        localGraph.inject(this);
        localGraph.inject(presenter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_timer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTaskTitle(String title) {
        taskTitleTextView.setText(title);
    }

    @Override
    public void setStatus(String status) {
        statusTextView.setText(status);
    }

    @Override
    public void setCurrentTime(long currentTime) {
        timeTextView.setText(formatTime(currentTime));
    }

    @Override
    public void setPomodoroCount(int pomodoroCount) {
        pomodorosTextView.setText(String.format(getString(R.string.pomodoro_count_format), pomodoroCount));
    }

    @Override
    public void setTotalTime(long totalTime) {
        totalTimeTextView.setText(formatTime(totalTime));
    }

    @Override
    public void enableButtons() {
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
    }

    @Override
    public void disableButtons() {
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
    }

    @Override
    public void showPomodoroDone() {
        //TODO show dialog
    }
}
