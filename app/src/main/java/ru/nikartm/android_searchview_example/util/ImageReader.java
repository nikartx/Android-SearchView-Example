package ru.nikartm.android_searchview_example.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import ru.nikartm.android_searchview_example.R;

/**
 * @author Ivan V on 29.10.2017.
 * @version 1.0
 */

public class ImageReader {

    public static void setCircleImage(Context context, ImageView image, String uri) {
        Glide.with(context)
                .load(uri)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.no_photo)
                        .error(R.drawable.no_photo)
                        .circleCrop())
                .into(image);
    }
}
