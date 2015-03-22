package co.ikust.pomodorotimer.rest.oauth;

import oauth.signpost.OAuthConsumer;

/**
 * OAuth {@link co.ikust.pomodorotimer.rest.oauth.TokenManager} for Trello.
 * <p>
 * Manages refreshing and storing access token key and secret.
 */
public class TrelloTokenManager implements TokenManager {

    @Override
    public void refreshToken(RefreshTokenCallback callback) {

    }

    @Override
    public boolean hasToken() {
        return false;
    }

    @Override
    public OAuthConsumer getToken() {
        return null;
    }
}
