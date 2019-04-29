package com.licj.apphub.commons.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by licj on 2017/7/7.
 */

public class Base64Encode {
    private static final String TAG = "Base64Encode";

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @param quality
     * @return
     */
    public String bitmapToBase64(Bitmap bitmap, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Log.d(TAG, "bitmap.length() = " + byteArray.length);
        String encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP);
        Log.d(TAG, "no_warp encoded : " + encoded);
        return encoded;
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public Bitmap base64ToBitmap(String base64Data) {
        byte[] byteArray = Base64.decode(base64Data, Base64.NO_WRAP);
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return bitmap;
    }
}
