package com.licj.apphub.commons.mvp;

import android.os.Bundle;
import android.view.View;

import butterknife.Unbinder;

/**
 * Created by licj on 2018/3/10.
 */

public interface IView<P> {
    void bindUI(View rootView);

    void bindEvent();

    void initData(Bundle savedInstanceState);

    int getOptionsMenuId();

    int getLayoutId();

    P newP();

    void unBindUI(Unbinder unbinder);
}
