package co.ikust.pomodorotimer.rest.models;

import com.google.gson.annotations.SerializedName;

/**
 * REST Model that represents Trello card.
 */
public class Card {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
