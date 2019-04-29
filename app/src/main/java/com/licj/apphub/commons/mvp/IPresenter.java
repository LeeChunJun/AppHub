package com.licj.apphub.commons.mvp;

/**
 * Created by licj on 2018/3/10.
 */

public interface IPresenter<V> {
    void attachV(V view);

    void detachV();
}
