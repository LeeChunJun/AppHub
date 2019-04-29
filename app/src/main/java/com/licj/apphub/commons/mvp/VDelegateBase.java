package com.licj.apphub.commons.mvp;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.licj.apphub.commons.initial.MyApplication;


/**
 * Created by licj on 2018/3/10.
 */

public class VDelegateBase implements VDelegate {

    private Context context;
    private Toast toast = null;

    private VDelegateBase(Context context) {
        this.context = context;
    }

    public static VDelegate create() {
        return new VDelegateBase(MyApplication.getContext());
    }

    @Override
    public void oncreate() {

    }

    @Override
    public void restart() {

    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void visible(boolean flag, View view) {
        if (flag) {
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void gone(boolean flag, View view) {
        if (flag) {
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public void invisible(boolean flag, View view) {
        if (flag) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void toastShort(String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    @Override
    public void toastLong(String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
