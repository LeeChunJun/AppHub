package com.licj.apphub.commons.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.licj.apphub.commons.initial.MyApplication;


/**
 * ----------------------------------------
 * 1.沉浸式状态栏 -> 设置状态栏的颜色
 * 2.APP全屏 -> 状态栏，ActionBar，导航栏隐藏
 * 3.APP视频播放 -> 隐藏ActionBar，留状态栏空间
 * ----------------------------------------
 * 1.取得软件版本号
 * ----------------------------------------
 * 1.像素单位转化
 * ----------------------------------------
 */
public class AppUtils {

    private static AppUtils instance;
    private Context context;

    private AppUtils() {
        context = MyApplication.getContext();
    }

    public static AppUtils getInstance() {
        if (instance == null) {
            synchronized (AppUtils.class) {
                if (instance == null) {
                    instance = new AppUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 设置状态栏的颜色
     */
    public static void statusBarTintColor(Activity activity, int color) {
        // 透明状态栏
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup androidContainer = activity.findViewById(android.R.id.content);
        // 留出高度 setFitsSystemWindows  true代表会调整布局，会把状态栏的高度留出来
        View contentView = androidContainer.getChildAt(0);
        if (contentView != null) {
            contentView.setFitsSystemWindows(true);
        }
        // 在原来的位置上添加一个状态栏
        View statusBarView = createStatusBarView(activity);
        androidContainer.addView(statusBarView, 0);
        statusBarView.setBackgroundColor(color);
    }

    /**
     * 创建一个需要填充statusBarView
     */
    private static View createStatusBarView(Activity activity) {
        View statusBarView = new View(activity);
        ViewGroup.LayoutParams statusBarParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(statusBarParams);
        return statusBarView;
    }

    /**
     * 获取状态栏的高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 状态栏，ActionBar，导航栏隐藏
     */
    public static void hideNavigationBarStatusBar(Activity activity, boolean hasFocus) {
        if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /**
     * 隐藏ActionBar，留状态栏空间
     */
    public static void hideActionBar(Activity activity, boolean isPadding) {
        if (isPadding) {
            ViewGroup androidContainer = activity.findViewById(android.R.id.content);
            // 留出高度 setFitsSystemWindows  true代表会调整布局，会把状态栏的高度留出来
            View contentView = androidContainer.getChildAt(0);
            if (contentView != null) {
                contentView.setFitsSystemWindows(true);
            }
        }

    }

    /**
     * 取得软件版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageInfo manager = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return manager.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown version!";
        }
    }

    /**
     * dp转换成px
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转换成dp
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转换成px
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转换成sp
     */
    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


}
