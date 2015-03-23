package co.ikust.pomodorotimer.utils;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.HashMap;

/**
 * <code>Activity</code> related utility methods.
 */
public class ActivityUtils {

    /**
     * Sets up <code>ActionBar</code> back arrow and title.
     */
    public static void setupBackButton(ActionBarActivity activity, int titleResId) {
        setupBackButton(activity, activity.getString(titleResId));
    }

    public static void setupBackButton(ActionBarActivity activity, String title) {
        activity.getSupportActionBar().setTitle(title);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public static void setupBackButton(Activity activity, int titleResId) {
        setupBackButton(activity, activity.getString(titleResId));
    }

    public static void setupBackButton(Activity activity, String title) {
        activity.getActionBar().setTitle(title);
        activity.getActionBar().setHomeButtonEnabled(true);
        activity.getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Converts a <code>Bundle</code> to <code>HashMap</code>.
     *
     * @param bundle
     * @return
     */
    public static HashMap<String, Object> bundleToHashMap(Bundle bundle) {
        HashMap<String, Object> hashMap = new HashMap<>();

        if(bundle != null) {
            for(String key : bundle.keySet()) {
                hashMap.put(key, bundle.get(key));
            }
        }

        return hashMap;
    }

}
