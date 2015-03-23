package co.ikust.pomodorotimer.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ivan on 20/03/15.
 */
public class Board {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("lists")
    private ArrayList<List> lists;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<List> getLists() {
        return lists;
    }
}
