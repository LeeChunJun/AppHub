package com.licj.apphub.commons.fragment;

import com.licj.apphub.commons.mvp.IPresenter;
import com.licj.apphub.commons.mvp.XFragment;

public abstract class MyFragment<P extends IPresenter> extends XFragment<P> {

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;

    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//
//        if(!hidden){
//            isVisible = true;
//            onVisible();
//        } else {
//            isVisible = false;
//            onInvisible();
//        }
//    }

    /**
     * 1.onCreateView()之前调用
     * 2.当fragment结合viewpager使用的时候才会调用
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        if(isVisible && rootView != null){
            lazyLoad();
        }
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();
}
