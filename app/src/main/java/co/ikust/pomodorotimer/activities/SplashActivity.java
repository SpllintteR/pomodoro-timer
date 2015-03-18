package co.ikust.pomodorotimer.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import co.ikust.pomodorotimer.R;
import co.ikust.pomodorotimer.rest.TrelloConstants;
import co.ikust.pomodorotimer.trello.Trello;
import co.ikust.pomodorotimer.trello.TrelloDialog;
import co.ikust.pomodorotimer.trello.TrelloError;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

/**
 * Created by ivan on 18/03/15.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
                TrelloConstants.APP_KEY,
                TrelloConstants.APP_SECRET
        );

        TrelloDialog trelloDialog = new TrelloDialog(this, consumer, "", new Trello.DialogListener() {
            @Override
            public void onComplete(String accessKey, String accessSecret) {
                Log.d("TrelloDialog", "AccessKey: " + accessKey);
                Log.d("TrelloDialog", "AccessSecret: " + accessSecret);
            }

            @Override
            public void onError(TrelloError error) {
                Log.d("TrelloDialog", "onError()");
                error.printStackTrace();
            }

            @Override
            public void onCancel() {
                Log.d("TrelloDialog", "onCancel()");
            }
        });

        trelloDialog.show();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
//                        TrelloConstants.APP_KEY,
//                        TrelloConstants.APP_SECRET
//                );
//
//                OAuthProvider oAuthProvider = new CommonsHttpOAuthProvider(
//                        TrelloConstants.REQUEST_URL,
//                        TrelloConstants.ACCESS_URL,
//                        TrelloConstants.AUTHORIZE_URL
//                );
//
//                try {
//                    String url = oAuthProvider.retrieveRequestToken(consumer, OAuth.OUT_OF_BAND);
//                    Log.d("OAuth", url);
//                } catch (OAuthMessageSignerException e) {
//                    e.printStackTrace();
//                } catch (OAuthNotAuthorizedException e) {
//                    e.printStackTrace();
//                } catch (OAuthExpectationFailedException e) {
//                    e.printStackTrace();
//                } catch (OAuthCommunicationException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }
}
