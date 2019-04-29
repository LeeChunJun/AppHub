package com.licj.apphub.commons.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.Unbinder;

/**
 * Created by licj on 2018/3/10.
 */

public abstract class XActivity<P extends IPresenter> extends AppCompatActivity implements IView<P> {

    protected Activity context;
    private VDelegate vDelegate;
    private P p;
    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindUI(null);
            bindEvent();
        }
        initData(savedInstanceState);
        getvDelegate().oncreate();

    }

    @Override
    public void bindEvent() {
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this);
    }

    @Override
    public void unBindUI(Unbinder unbinder) {
        KnifeKit.unbind(unbinder);
    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create();
        }
        return vDelegate;
    }

    protected P getP() {
        if (p == null) {
            p = newP();
            if (p != null) {// 部分view不需要present
                p.attachV(this);
            }
        }
        return p;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getvDelegate().restart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getvDelegate().start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getvDelegate().resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getvDelegate().pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getvDelegate().stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getP() != null) {
            getP().detachV();
        }
        unBindUI(unbinder);
        getvDelegate().destroy();
        p = null;
        vDelegate = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getOptionsMenuId() > 0) {
            getMenuInflater().inflate(getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

}
