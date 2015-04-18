package co.ikust.pomodorotimer.rest;

import co.ikust.pomodorotimer.rest.models.Board;
import co.ikust.pomodorotimer.rest.models.List;
import co.ikust.pomodorotimer.rest.models.Member;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Interface that defines REST APIs used in application.
 */
public interface RestService {

    @GET("/1/members/me?fields=fullName&boards=all&board_fields=name")
    void getMember(
            Callback<Member> memberCallback
    );

    @GET("/1/boards/{id}?fields=id,name&lists=all&list_fields=name")
    void getBoard(
            @Path("id") String boardId,
            Callback<Board> callback
    );

    @GET("/1/lists/{id}?fields=name&cards=open&card_fields=name")
    void getList(
            @Path("id") String listId,
            Callback<List> callback
    );

    @PUT("/1/cards/{cardId}")
    void moveCard(
            @Path("cardId") String cardId,
            @Query("idList") String listId,
            Callback<Object> callback
    );

    @POST("/1/cards/{cardId}/actions/comments")
    void addComment(
            @Path("cardId") String cardId,
            @Query("text") String comment,
            Callback<Object> callback
    );

}
