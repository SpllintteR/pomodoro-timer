package co.ikust.pomodorotimer.activities;

import com.viewpagerindicator.TabPageIndicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.ikust.pomodorotimer.R;
import co.ikust.pomodorotimer.adapters.ViewPagerAdapter;
import co.ikust.pomodorotimer.fragments.TaskListFragment;
import co.ikust.pomodorotimer.rest.models.Board;
import co.ikust.pomodorotimer.rest.models.List;
import co.ikust.pomodorotimer.rest.models.Member;
import co.ikust.pomodorotimer.storage.models.TaskTime;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.getRestService;


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

        getRestService().getMember(new Callback<Member>() {
            @Override
            public void success(Member member, Response response) {
                Log.d("Member", "Member fetch success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Member", "Member fetch onError");
                error.printStackTrace();
            }
        });

        getRestService().getBoard("55089da79ebef1e2e6dbf9fe", new Callback<Board>() {
            @Override
            public void success(Board board, Response response) {
                Log.d("Board", "Board fetch success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Board", "Board fetch onError");
                error.printStackTrace();
            }
        });

        getRestService().getList("55089da79ebef1e2e6dbfa00", new Callback<List>() {
            @Override
            public void success(List list, Response response) {
                Log.d("List", "List fetch success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("List", "List fetch onError");
                error.printStackTrace();
            }
        });

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
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    public void startTimer(TaskTime task) {
        //TODO move card to doing list

        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }
    //endregion
}
