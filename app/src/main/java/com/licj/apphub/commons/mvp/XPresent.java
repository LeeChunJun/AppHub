package com.licj.apphub.commons.mvp;

import java.lang.ref.WeakReference;

/**
 * Created by licj on 2018/3/10.
 */

public class XPresent<V extends IView> implements IPresenter<V> {
    private WeakReference<V> v;

    @Override
    public void attachV(V view) {
        v = new WeakReference<>(view);
    }

    @Override
    public void detachV() {
        if (v.get() != null) {
            v.clear();
        }
        v = null;
    }

    protected V getV() {
        if (v == null) {
            throw new IllegalStateException("v can not be null!");
        }
        return v.get();
    }
}
