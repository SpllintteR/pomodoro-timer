package co.ikust.pomodorotimer.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import co.ikust.pomodorotimer.R;

/**
 * Utility methods for showing dialogs of various sorts.
 */
public class DialogUtils {


    /**
     * Show an info dialog.
     */
    public static void showInfo(Activity activity, String message, DialogInterface.OnClickListener okListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.app_name);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, okListener);

        if (activity != null && !activity.isFinishing()) {
            builder.show();
        }
    }



    public static void showAreYouSure(Activity activity, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.app_name);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.yes, listener);
        builder.setNegativeButton(R.string.no, null);

        if (activity != null && !activity.isFinishing()) {
            builder.show();
        }
    }


    /**
     * Show a dialog containing given onError message.
     */
    public static void showError(Activity activity, String message) {
        showError(activity, message, null);
    }

    /**
     * Show a dialog containing given onError message.
     */
    public static void showError(Activity activity, String message, DialogInterface.OnClickListener okListener) {
        showError(activity, message, true, okListener);
    }

    /**
     * Show a dialog containing given onError message.
     */
    public static void showError(Activity activity, String message, boolean isCancelable, DialogInterface.OnClickListener okListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.error);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, okListener);
        builder.setCancelable(isCancelable);

        if (activity != null && !activity.isFinishing()) {
            builder.show();
        }
    }






}
