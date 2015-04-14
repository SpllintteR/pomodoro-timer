package co.ikust.pomodorotimer.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import co.ikust.pomodorotimer.R;

import static co.ikust.pomodorotimer.PomodoroTimerApplication.hasAccessToken;

/**
 * Created by ivan on 18/03/15.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(hasAccessToken()) {
            //TODO: sync with Trello -> load current user data, boards and lists

            Intent intent = new Intent(this, TasksActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, TasksActivity.class);
            startActivity(intent);
        }



    }
}
