package co.ikust.pomodorotimer.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * REST Model that representes Trello List.
 */
public class List {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("cards")
    private ArrayList<Card> cards;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
