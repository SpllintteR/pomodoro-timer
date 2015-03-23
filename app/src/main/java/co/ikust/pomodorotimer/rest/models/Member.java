package co.ikust.pomodorotimer.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * REST Model that holds Trello member data and board list.
 */
public class Member {

    @SerializedName("id")
    private String id;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("boards")
    private ArrayList<Board> boards;

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public ArrayList<Board> getBoards() {
        return boards;
    }
}
