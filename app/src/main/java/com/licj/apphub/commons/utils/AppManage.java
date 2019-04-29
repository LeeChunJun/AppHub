package com.licj.apphub.commons.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;


import com.licj.apphub.commons.activity.MyActivity;

import java.util.Stack;


/**
 * Created by licj on 2018/3/10.
 */

public class AppManage {
    private static final String TAG = "AppManage";

    private static Stack<MyActivity> activityStack;
    private static AppManage instance;

    private AppManage() {
    }

    /**
     * 单一实例
     */
    public static AppManage getAppManager() {
        if (instance == null) {
            synchronized (AppManage.class) {
                if (instance == null) {
                    instance = new AppManage();
                }
            }
        }
        return instance;
    }

    /**
     * 添加MyActivity到堆栈
     */
    public void addActivity(MyActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前MyActivity（堆栈中最后一个压入的）
     */
    public MyActivity currentActivity() {
        MyActivity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前MyActivity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        MyActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的MyActivity
     */
    public void finishActivity(MyActivity activity) {
        if (activity != null) {
            activity.finishRe();
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (MyActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (MyActivity activity : activityStack) {
            if (!activity.isFinishing()) {
                activity.finishRe();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            Log.e(TAG, "AppExit Error!");
        }
    }

}