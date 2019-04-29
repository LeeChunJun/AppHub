package com.licj.apphub.commons.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.licj.apphub.R;

@SuppressLint("AppCompatCustomView")
public class SafeEditText extends EditText implements TextWatcher {
    private static final String TAG = "SafeEditText";

    private OnEditTextKeyBackListener listener;
    private Drawable mClearDrawable;// 删除按钮的引用
    private Drawable mSearchDrawable;// 查询按钮的引用

    public SafeEditText(Context context) {
        this(context, null);
    }

    public SafeEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public SafeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 获取EditText的DrawableRight，假如没有设置我们就使用默认的图片
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(R.drawable.ic_delete);
        }
        mSearchDrawable = getCompoundDrawables()[0];
        if (mSearchDrawable == null) {
            mSearchDrawable = getResources().getDrawable(R.drawable.ic_search);
        }
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        mSearchDrawable.setBounds(0, 0, mSearchDrawable.getIntrinsicWidth(), mSearchDrawable.getIntrinsicHeight());
        // 默认设置隐藏图标
        setClearIconVisible(false);
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable) {
                    this.setText("");
                    // 设置晃动效果
                    setShakeAnimation();
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(mSearchDrawable,
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    /**
     * 设置晃动动画
     */
    public void setShakeAnimation(){
        this.setAnimation(shakeAnimation(5));
    }

    /**
     * 晃动动画
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts){
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    @Override
    public boolean performLongClick() {
        return super.performLongClick();
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (listener != null) {
                return listener.onKeyBack();
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }

    public void setOnEditTextKeyBackListener(OnEditTextKeyBackListener listener) {
        this.listener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.d(TAG, "beforeTextChanged -> " + s);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d(TAG, "onTextChanged -> " + s);
        setClearIconVisible(s.length() > 0);
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.d(TAG, "afterTextChanged -> " + s);
    }

    public interface OnEditTextKeyBackListener {
        boolean onKeyBack();
    }
}
