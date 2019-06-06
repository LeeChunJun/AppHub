package com.licj.wehome.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by licj on 2017/8/8.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        // Glide 加载图片简单用法
        Glide.with(context).load((String) path).into(imageView);
    }
}
