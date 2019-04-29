package com.licj.apphub.index;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.licj.apphub.R;
import com.licj.apphub.commons.fragment.MyFragment;
import com.licj.apphub.commons.utils.KeyboardLayout;
import com.licj.apphub.commons.views.IndexToolBar;

import butterknife.BindView;

public class IndexFragment extends MyFragment<PIndexFragment> implements KeyboardLayout.KeyboardLayoutListener {

    private static final String TAG = "IndexFragment";

    @BindView(R.id.tb_index)
    IndexToolBar tbIndex;

    private KeyboardLayout keyboardLayout = null;
    private boolean isSoftClosed = true;
    private boolean mHandledPress = false;
    private boolean mFocus = false;

    public IndexFragment() {
    }

    public static IndexFragment newInstance() {
        IndexFragment fragment = new IndexFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        // ETSearch的点击事件
        tbIndex.setOnETSearchEditorActionListener(new IndexToolBar.OnETSearchEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getvDelegate().toastShort(v.getText().toString());
                }
                return false;
            }
        });

        // ETSearch的获取焦点事件
        tbIndex.setOnETSearchFocusChangeListener(new IndexToolBar.OnETSearchFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mFocus = hasFocus;// 告知Fragment，ETSearch获取焦点情况
                if (hasFocus) {
                    mHandledPress = false;
                    switchTBIndexView(false);
                }
            }
        });

        // ETSearch的收起键盘事件
        tbIndex.setOnETSearchKeyBackListener(new IndexToolBar.OnETSearchKeyBackListener() {
            @Override
            public boolean onKeyBack() {
                isSoftClosed = true;
                mFocus = true;
                mHandledPress = false;
                return onBackPressed();
            }
        });

        // 通过软键盘上的按钮关闭软键盘
        keyboardLayout = new KeyboardLayout(context, tbIndex.getmETSearch());
        keyboardLayout.setKeyboardListener(this);

        // tbIndex的点击返回事件 -> 使得ETSearch触发失去焦点事件
        tbIndex.setOnIBMenuBackClickListener(new IndexToolBar.OnIBMenuBackClickListener() {
            @Override
            public void onClick() {
                tbIndex.setETSearchFocusChange(context);
                mHandledPress = false;
                switchTBIndexView(true);
            }
        });

    }

    /**
     * 切换TBIndex界面
     *
     * @param isInitial
     */
    public void switchTBIndexView(boolean isInitial) {
        if (isInitial) {
            tbIndex.showToolBarController();
            tbIndex.hideMenuBack();
            tbIndex.hideSearchText();
        } else {
            tbIndex.hideToolBarController();
            tbIndex.showMenuBack();
            tbIndex.showSearchText();
        }
    }

    /**
     * 在ETSearch获取焦点的时候，点击返回回退按钮 -> mHandledPress = true
     *
     * @return
     */
    public boolean onBackPressed() {
        Log.d(TAG, "before onBackPressed-> mFocus:" + mFocus + " || mHandledPress:" + mHandledPress);
        if (mFocus && !mHandledPress) {
            tbIndex.setETSearchFocusChange(context);
            mHandledPress = true;
            Log.d(TAG, "m1 onBackPressed-> mFocus:" + mFocus + " || mHandledPress:" + mHandledPress);
            return true;
        } else if (!mFocus && mHandledPress) {
            switchTBIndexView(true);
            mHandledPress = false;
            Log.d(TAG, "m2 onBackPressed-> mFocus:" + mFocus + " || mHandledPress:" + mHandledPress);
            return true;
        }
        Log.d(TAG, "after onBackPressed-> mFocus:" + mFocus + " || mHandledPress:" + mHandledPress);
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    public PIndexFragment newP() {
        return new PIndexFragment(context);
    }

    @Override
    public void onKeyboardStateChanged(boolean isActive, int keyboardHeight) {
        if (isActive) {
            isSoftClosed = false;
        } else {
            // 软键盘关闭
            if (!isSoftClosed) {
                isSoftClosed = true;
                mFocus = true;
                mHandledPress = false;
                onBackPressed();
            }
        }
    }

}
