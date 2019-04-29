package com.licj.apphub.commons.mvp;

import android.app.Activity;

/**
 * Created by licj on 2018/3/11.
 */

public interface RouterCallback {

    void onBefore(Activity from, Class<?> to);

    void onNext(Activity from, Class<?> to);

    void onError(Activity from, Class<?> to, Throwable throwable);
}
