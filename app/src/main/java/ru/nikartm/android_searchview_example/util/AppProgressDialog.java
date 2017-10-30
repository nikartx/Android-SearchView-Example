package ru.nikartm.android_searchview_example.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Progress dialog
 * @author Ivan V on 29.10.2017.
 * @version 1.0
 */
public class AppProgressDialog {

    private static ProgressDialog progress;

    public static void show(Context context) {
        if (progress == null) {
            progress = new ProgressDialog(context);
            progress.setMessage("Please wait...");
            progress.setCanceledOnTouchOutside(false);
            progress.setCancelable(false);
            progress.show();
        }
    }

    public static void dismiss() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
        progress = null;
    }
}
