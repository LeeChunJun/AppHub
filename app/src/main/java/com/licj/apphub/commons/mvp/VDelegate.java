package com.licj.apphub.commons.mvp;

import android.view.View;

/**
 * Created by licj on 2018/3/10.
 */

public interface VDelegate {
    void oncreate();

    void restart();

    void start();

    void resume();

    void pause();

    void stop();

    void destroy();

    void visible(boolean flag, View view);

    void gone(boolean flag, View view);

    void invisible(boolean flag, View view);

    void toastShort(String msg);

    void toastLong(String msg);
}
