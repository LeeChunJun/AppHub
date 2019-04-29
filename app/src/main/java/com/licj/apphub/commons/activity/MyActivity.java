package com.licj.apphub.commons.activity;

import android.os.Bundle;
import android.view.View;

import com.licj.apphub.R;
import com.licj.apphub.commons.mvp.IPresenter;
import com.licj.apphub.commons.mvp.XActivity;
import com.licj.apphub.commons.utils.AppManage;
import com.licj.apphub.commons.utils.AppUtils;


/**
 * Created by licj on 2018/3/10.
 */

public abstract class MyActivity<P extends IPresenter> extends XActivity<P> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManage.getAppManager().addActivity(this);
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        // 1.沉浸式状态栏
//        AppUtils.statusBarTintColor(context, getResources().getColor(R.color.colorPrimary));
        // 2.APP全屏
//        AppUtils.hideNavigationBarStatusBar(context, true);
        // 3.APP视频播放
//        AppUtils.hideActionBar(context, true);
    }

    @Override
    public void finish() {
        AppManage.getAppManager().finishActivity();
    }

    public void finishRe() {
        super.finish();
    }

}
