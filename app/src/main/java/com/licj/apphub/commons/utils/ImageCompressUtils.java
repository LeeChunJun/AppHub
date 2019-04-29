package com.licj.apphub.commons.utils;


import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片压缩工具类
 * Created by licj on 2018/3/10.
 */
public class ImageCompressUtils {
    private static final String TAG = "ImageCompressUtils";

    /**
     * 通过降低图片的质量来压缩图片
     *
     * @param bitmap  要压缩的图片
     * @param maxSize 压缩后图片大小的最大值,单位KB
     * @return 压缩后的图片
     */
    public static Bitmap compressByQuality1(Bitmap bitmap, int maxSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        bitmap.compress(CompressFormat.JPEG, quality, baos);
        Log.d(TAG, "BitmapCompressByQuality，图片压缩前大小：" + baos.toByteArray().length + "byte");
        while (baos.toByteArray().length / 1024 > maxSize) {
            quality -= 10;
            baos.reset();
            bitmap.compress(CompressFormat.JPEG, quality, baos);
            Log.d(TAG, "BitmapCompressByQuality，质量压缩到原来的" + quality + "%时大小为："
                    + baos.toByteArray().length + "byte");
        }
        Log.d(TAG, "BitmapCompressByQuality，图片压缩后大小：" + baos.toByteArray().length + "byte");
        bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length);
        return bitmap;
    }

    public static Bitmap compressByQuality2(Bitmap bitmap, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, baos);
        Log.d(TAG, "BitmapCompressByQuality，图片压缩前大小：" + baos.toByteArray().length + "byte");
        bitmap.compress(CompressFormat.JPEG, quality, baos);
        Log.d(TAG, "BitmapCompressByQuality，图片压缩后大小：" + baos.toByteArray().length + "byte");
        bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length);
        return bitmap;
    }

    /**
     * 通过压缩图片的尺寸来压缩图片大小
     *
     * @param pathName     图片的完整路径
     * @param targetWidth  缩放的目标宽度
     * @param targetHeight 缩放的目标高度
     * @return 缩放后的图片
     */
    public static Bitmap compressBySize(String pathName, int targetWidth, int targetHeight) {
        // 1.不去真的解析图片，只是获取图片的头部信息，包含宽高等
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
        // 2.得到图片的宽度、高度
        int imgWidth = opts.outWidth;
        int imgHeight = opts.outHeight;
        // 3.分别计算图片宽度、高度与目标宽度、高度的比例；取大于等于该比例的最小整数
        int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
        int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
        if (widthRatio > 1 || heightRatio > 1) {
            if (widthRatio > heightRatio) {
                opts.inSampleSize = widthRatio;
            } else {
                opts.inSampleSize = heightRatio;
            }
        }
        // 4.设置好缩放比例后，加载图片进内存
        opts.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(pathName, opts);
        return bitmap;
    }

    /**
     * 通过压缩图片的尺寸来压缩图片大小
     *
     * @param bitmap       要压缩图片
     * @param targetWidth  缩放的目标宽度
     * @param targetHeight 缩放的目标高度
     * @return 缩放后的图片
     */
    public static Bitmap compressBySize(Bitmap bitmap, int targetWidth, int targetHeight) {
        // 1.不去真的解析图片，只是获取图片的头部信息，包含宽高等
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, baos);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        Bitmap newBitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
                baos.toByteArray().length, opts);
        // 2.得到图片的宽度、高度
        int imgWidth = opts.outWidth;
        int imgHeight = opts.outHeight;
        // 3.分别计算图片宽度、高度与目标宽度、高度的比例；取大于该比例的最小整数
        int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
        int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
        if (widthRatio > 1 || heightRatio > 1) {
            if (widthRatio > heightRatio) {
                opts.inSampleSize = widthRatio;
            } else {
                opts.inSampleSize = heightRatio;
            }
        }
        // 4.设置好缩放比例后，加载图片进内存
        opts.inJustDecodeBounds = false;
        newBitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
                baos.toByteArray().length, opts);
        return newBitmap;
    }

    /**
     * 通过压缩图片的尺寸来压缩图片大小，通过读入流的方式，可以有效防止网络图片数据流形成位图对象时内存过大的问题；
     *
     * @param is           要压缩图片，以流的形式传入
     * @param targetWidth  缩放的目标宽度
     * @param targetHeight 缩放的目标高度
     * @return 缩放后的图片
     * @throws IOException 读输入流的时候发生异常
     */
    public static Bitmap compressBySize(InputStream is, int targetWidth,
                                        int targetHeight) throws IOException {
        // 1.不去真的解析图片，只是获取图片的头部信息，包含宽高等
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len;
        while ((len = is.read(buff)) != -1) {
            baos.write(buff, 0, len);
        }
        byte[] data = baos.toByteArray();
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
        // 2.得到图片的宽度、高度；
        int imgWidth = opts.outWidth;
        int imgHeight = opts.outHeight;
        // 3.分别计算图片宽度、高度与目标宽度、高度的比例；取大于该比例的最小整数；
        int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
        int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
        if (widthRatio > 1 || heightRatio > 1) {
            if (widthRatio > heightRatio) {
                opts.inSampleSize = widthRatio;
            } else {
                opts.inSampleSize = heightRatio;
            }
        }
        // 4.设置好缩放比例后，加载图片进内存；
        opts.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
        return bitmap;
    }

}
