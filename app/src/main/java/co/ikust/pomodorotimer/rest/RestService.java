package co.ikust.pomodorotimer.rest;

import java.util.ArrayList;

import co.ikust.pomodorotimer.rest.models.Board;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Interface that defines REST APIs used in application.
 */
public interface RestService {


    @GET("/1/members/me/boards?fields=id,name&lists=all&list_fields=name")
    void getBoards(Callback<ArrayList<Board>> callback);


}
