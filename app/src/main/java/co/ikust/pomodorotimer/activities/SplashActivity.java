package co.ikust.pomodorotimer.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import co.ikust.pomodorotimer.PomodoroTimerApplication;
import co.ikust.pomodorotimer.R;
import co.ikust.pomodorotimer.rest.models.Board;
import co.ikust.pomodorotimer.rest.oauth.TokenManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
            //TODO: sync with Trello

            Intent intent = new Intent(this, TasksActivity.class);
            startActivity(intent);
        } else {
//            Intent intent = new Intent(this, ConfigActivity.class);
//            startActivity(intent);

            PomodoroTimerApplication.refreshAccessToken(this, new TokenManager.RefreshTokenCallback() {
                @Override
                public void onComplete() {

                }

                @Override
                public void onError(Throwable error) {
                    Log.d("Refresh", "onError");
                    error.printStackTrace();
                }

                @Override
                public void onCancel() {
                    Log.d("Refresh", "onCancel");
                }
            });
        }

    }
}
