package co.ikust.pomodorotimer.rest;

import java.util.ArrayList;

import co.ikust.pomodorotimer.rest.models.Board;
import co.ikust.pomodorotimer.rest.models.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Interface that defines REST APIs used in application.
 */
public interface RestService {


    @GET("/1/members/me/boards?fields=id,name&lists=all&list_fields=name")
    void getBoards(
            Callback<ArrayList<Board>> callback
    );

    @GET("/1/lists/{id}?fields=name&cards=open&card_fields=name")
    void getList(
            @Path("id") String listId,
            Callback<List> callback
    );

}
