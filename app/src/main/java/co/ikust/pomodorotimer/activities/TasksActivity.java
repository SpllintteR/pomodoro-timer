package co.ikust.pomodorotimer.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import co.ikust.pomodorotimer.R;
import co.ikust.pomodorotimer.rest.models.Board;
import co.ikust.pomodorotimer.rest.models.List;
import co.ikust.pomodorotimer.rest.models.Member;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.getRestService;


public class TasksActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        getRestService().getMember(new Callback<Member>() {
            @Override
            public void success(Member member, Response response) {
                Log.d("Member", "Member fetch success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Member", "Member fetch error");
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
                Log.d("Board", "Board fetch error");
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
                Log.d("List", "List fetch error");
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
}
