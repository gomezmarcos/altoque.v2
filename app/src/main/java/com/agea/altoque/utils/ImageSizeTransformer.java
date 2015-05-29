package com.agea.altoque.utils;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by marcos on 27/05/15.
 */
public abstract class ImageSizeTransformer {
    public static int toPixels(int dp, DisplayMetrics metrics) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }
}
