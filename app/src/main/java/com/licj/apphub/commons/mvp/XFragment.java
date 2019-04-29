package com.licj.apphub.commons.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.Unbinder;

/**
 * Created by licj on 2018/3/10.
 */

public abstract class XFragment<P extends IPresenter> extends Fragment implements IView<P> {

    protected Activity context;
    protected LayoutInflater layoutInflater;
    protected View rootView;
    private VDelegate vDelegate;
    private P p;
    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutInflater = inflater;
        if (rootView == null && getLayoutId() > 0) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            bindUI(rootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindEvent();
        initData(savedInstanceState);
    }

    @Override
    public void bindEvent() {
    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.bind(this, rootView);
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
            if (p != null) {
                p.attachV(this);
            }
        }
        return p;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.context = (Activity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getP() != null) {
            getP().detachV();
        }
        unBindUI(unbinder);
        getvDelegate().destroy();

        p = null;
        vDelegate = null;
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

}

