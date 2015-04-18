package co.ikust.pomodorotimer.activities;

import com.viewpagerindicator.TabPageIndicator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.ikust.pomodorotimer.R;
import co.ikust.pomodorotimer.adapters.ViewPagerAdapter;
import co.ikust.pomodorotimer.fragments.TaskListFragment;
import co.ikust.pomodorotimer.rest.models.Card;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.getLocalData;
import static co.ikust.pomodorotimer.PomodoroTimerApplication.getRestService;
import static co.ikust.pomodorotimer.PomodoroTimerApplication.hasAccessToken;


public class TasksActivity extends ActionBarActivity implements TaskListFragment.TaskListCallbacks {

    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    @InjectView(R.id.tpiIndicator)
    TabPageIndicator viewPagerIndicator;

    @InjectView(R.id.vpContent)
    ViewPager viewPager;

    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        ButterKnife.inject(this);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        if (!hasAccessToken()) {
            showSettings();
        } else {
            addTabs();
        }

        viewPager.setAdapter(adapter);
        viewPagerIndicator.setViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (hasAccessToken() && adapter.getCount() == 0) {
            addTabs();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tasks, menu);
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
            showSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addTabs() {
        adapter.addItem(TaskListFragment.newInstance(getLocalData().getToDoList().getId()), getString(R.string.todo_list));
        adapter.addItem(TaskListFragment.newInstance(getLocalData().getDoingList().getId()), getString(R.string.doing_list));
        adapter.addItem(TaskListFragment.newInstance(getLocalData().getDoneList().getId()), getString(R.string.done_list));
        adapter.notifyDataSetChanged();
    }

    private void showSettings() {
        Intent intent = new Intent(this, ConfigActivity.class);
        startActivity(intent);
    }

    //region TaskListCallbacks implementation
    @Override
    public void showLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void startTimer(final Card task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.start_time_tracking));
        builder.setMessage(String.format(getString(R.string.start_time_tracking_message), task.getName()));
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getRestService().moveCard(
                        task.getId(),
                        getLocalData().getDoingList().getId(),
                        new Callback<Object>() {
                            @Override
                            public void success(Object o, Response response) {
                                Intent intent = new Intent(TasksActivity.this, TimerActivity.class);
                                intent.putExtra(TimerActivity.EXTRA_CARD, task);
                                startActivity(intent);
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                if (error.getResponse() != null && error.getResponse().getStatus() == 401) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TasksActivity.this);
                                    builder.setTitle(R.string.error);
                                    builder.setMessage("Your Trello token has expired, please refresh it.");
                                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(TasksActivity.this, ConfigActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(TasksActivity.this);
                                    builder.setTitle(R.string.error);
                                    builder.setMessage(getString(R.string.internet_connection_error));
                                    builder.setPositiveButton(R.string.ok, null);
                                    builder.show();
                                }
                            }
                        });
            }
        });

        builder.setNegativeButton(R.string.no, null);
        builder.show();
    }
    //endregion
}
