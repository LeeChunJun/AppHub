package com.licj.apphub.commons.initial;

import android.app.Application;
import android.content.Context;


/**
 * Created by licj on 2018/3/10.
 */


public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }
}
