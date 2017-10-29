package ru.nikartm.android_searchview_example.util;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Ivan V on 30.10.2017.
 * @version 1.0
 */
public class Util {

    /**
     * Force hide keyboard if open
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (imm != null && view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
