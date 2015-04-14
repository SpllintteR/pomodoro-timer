package co.ikust.pomodorotimer.activities;

import com.viewpagerindicator.TabPageIndicator;

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

import static co.ikust.pomodorotimer.PomodoroTimerApplication.hasAccessToken;


public class TasksActivity extends ActionBarActivity implements TaskListFragment.TaskListCallbacks {

    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    @InjectView(R.id.tpiIndicator)
    TabPageIndicator viewPagerIndicator;

    @InjectView(R.id.vpContent)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        ButterKnife.inject(this);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addItem(TaskListFragment.newInstance(""), getString(R.string.todo_list));
        adapter.addItem(TaskListFragment.newInstance(""), getString(R.string.doing_list));
        adapter.addItem(TaskListFragment.newInstance(""), getString(R.string.done_list));

        viewPager.setAdapter(adapter);
        viewPagerIndicator.setViewPager(viewPager);

        if(!hasAccessToken()) {
            showSettings();
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

    private void showSettings() {
        Intent intent = new Intent(this, ConfigActivity.class);
        startActivity(intent);
    }

    //region TaskListCallbacks implementation
    @Override
    public void showLoading(boolean loading) {
        if(loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void startTimer(Card task) {
        //TODO move card to doing list

        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }
    //endregion
}
