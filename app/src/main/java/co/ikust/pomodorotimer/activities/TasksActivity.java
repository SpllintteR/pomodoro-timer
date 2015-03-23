package co.ikust.pomodorotimer.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import co.ikust.pomodorotimer.PomodoroTimerApplication;
import co.ikust.pomodorotimer.R;
import co.ikust.pomodorotimer.rest.models.Board;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class TasksActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        PomodoroTimerApplication.getRestService().getBoards(new Callback<ArrayList<Board>>() {
            @Override
            public void success(ArrayList<Board> board, Response response) {
                Log.d("Config", "Board fetch success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Config", "Board fetch error");
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
