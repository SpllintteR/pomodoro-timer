package co.ikust.pomodorotimer.rest.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import co.ikust.pomodorotimer.storage.models.TrackedTime;

/**
 * REST Model that represents Trello card.
 */
public class Card implements Serializable{

    @SerializedName("list_id")
    private String listId;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("task_time")
    private TrackedTime trackedTime;

    public Card() {
        //Initialize tracked time with default values.
        this.trackedTime = new TrackedTime();
    }

    public String getListId() {
        return listId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TrackedTime getTrackedTime() {
        return trackedTime;
    }
}
