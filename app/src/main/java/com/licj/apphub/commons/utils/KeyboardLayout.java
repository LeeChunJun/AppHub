package com.licj.apphub.commons.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

public class KeyboardLayout {

    private KeyboardLayoutListener mListener;
    private boolean mIsKeyboardActive = false; // 输入法是否激活
    private int mKeyboardHeight = 0; // 输入法高度

    public KeyboardLayout(Activity activity, View view) {
        // 通过视图树监听布局变化
        view.getViewTreeObserver().addOnGlobalLayoutListener(new KeyboardOnGlobalChangeListener(activity));
    }

    public KeyboardLayoutListener getKeyboardListener() {
        return mListener;
    }

    public void setKeyboardListener(KeyboardLayoutListener listener) {
        mListener = listener;
    }

    public boolean isKeyboardActive() {
        return mIsKeyboardActive;
    }

    /**
     * 获取输入法高度
     *
     * @return
     */
    public int getKeyboardHeight() {
        return mKeyboardHeight;
    }

    public interface KeyboardLayoutListener {
        /**
         * @param isActive       输入法是否激活
         * @param keyboardHeight 输入法面板高度
         */
        void onKeyboardStateChanged(boolean isActive, int keyboardHeight);
    }

    private class KeyboardOnGlobalChangeListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private int mScreenHeight = 0;
        private Rect mRect = new Rect();
        private Activity mActivity;

        public KeyboardOnGlobalChangeListener(Activity mActivity) {
            this.mActivity = mActivity;
        }

        private int getScreenHeight() {
            if (mScreenHeight > 0) {
                return mScreenHeight;
            }
            mScreenHeight = mActivity.getWindow().getDecorView().getRootView().getHeight();
            return mScreenHeight;
        }

        @Override
        public void onGlobalLayout() {
            // 获取当前页面窗口的显示范围
            mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(mRect);

            int screenHeight = getScreenHeight(); // 屏幕高度
            int keyboardHeight = screenHeight - mRect.bottom; // 输入法的高度
            boolean isActive = false;
            if (Math.abs(keyboardHeight) > screenHeight / 3) {
                isActive = true; // 超过屏幕三分之一则表示弹出了输入法
                mKeyboardHeight = keyboardHeight;
            }
            mIsKeyboardActive = isActive;
            if (mListener != null) {
                mListener.onKeyboardStateChanged(isActive, keyboardHeight);
            }
        }
    }

}


