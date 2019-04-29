package com.licj.apphub.commons.mvp;

import android.content.Context;
import android.content.SharedPreferences;

import com.licj.apphub.commons.initial.MyApplication;


/**
 * Created by licj on 2018/3/19.
 */

public class SharedPref implements ICache {

    private static String SP_NAME = MVPConf.CACHE_SP_NAME;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static SharedPref instance;

    private SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SharedPref getInstance() {
        if (instance == null) {
            synchronized (SharedPref.class) {
                if (instance == null) {
                    instance = new SharedPref(MyApplication.getContext());
                }
            }
        }
        return instance;
    }

    @Override
    public void remove(String key) {
        editor.remove(key).apply();
    }

    @Override
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    @Override
    public void clear() {
        editor.clear().apply();
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value).apply();
    }

    public int getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public void putLong(String key, long value) {
        editor.putLong(key, value).apply();
    }

    public long getLong(String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    public void putFloat(String key, float value) {
        editor.putFloat(key, value).apply();
    }

    public float getFloat(String key, float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public void putString(String key, String value) {
        editor.putString(key, value).apply();
    }

    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public void put(String key, Object value) {
    }
}
