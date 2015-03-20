package co.ikust.pomodorotimer.rest.oauth;

import co.ikust.pomodorotimer.trello.Trello;
import oauth.signpost.OAuthConsumer;

/**
 * Created by ivan on 20/03/15.
 */
public interface TokenManager {

    void refreshToken(Trello.DialogListener callback); //replace with another callback

    boolean hasToken();

    OAuthConsumer getToken();
}
