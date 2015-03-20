package co.ikust.pomodorotimer.rest;

import co.ikust.pomodorotimer.rest.models.Board;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Interface that defines REST APIs used in application.
 */
public interface RestService {


    @GET("/1/members/me/boards")
    void getBoards(Callback<Board> callback);


}
