package com.licj.apphub.commons.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;

import com.licj.apphub.R;

public class IndexToolBar extends Toolbar {

    private static final String TAG = "IndexToolBar";

    private boolean isShowMenuBack;
    private boolean isShowSearchText;
    private boolean isShowToolBarController;
    private CharSequence middleEditText;

    private View mView;
    private ImageButton mIBMenuBack;
    private SafeEditText mETSearch;
    private TextView mTVSearch;
    private LinearLayout mLLController;
    private ImageButton mIBLocal;
    private ImageButton mIBHistory;
    private ImageButton mIBDownload;

    public IndexToolBar(Context context) {
        this(context, null);
    }

    public IndexToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("RestrictedApi")
    public IndexToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            /**
             * 读取自定义属性
             */
            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.IndexToolbar, defStyleAttr, 0);
            /**
             * 获取xml该元素是否存在的属性
             */
            isShowMenuBack = a.getBoolean(R.styleable.IndexToolbar_ShowMenuBack, false);
            isShowSearchText = a.getBoolean(R.styleable.IndexToolbar_ShowSearchText, false);
            isShowToolBarController = a.getBoolean(R.styleable.IndexToolbar_ShowToolBarController, false);
            /**
             * 各元素引用对象
             */
            middleEditText = a.getString(R.styleable.IndexToolbar_EditText);

            a.recycle();
            initView();
            initListener();
        }
    }

    /**
     * 初始化控件
     */
    public void initView() {
        if (mView == null) {
            mView = View.inflate(getContext(), R.layout.toolbar_index, null);

            mIBMenuBack = mView.findViewById(R.id.toolbar_menu_back);
            mETSearch = mView.findViewById(R.id.et_search);
            mTVSearch = mView.findViewById(R.id.tv_search);
            mLLController = mView.findViewById(R.id.ll_toolbar_controller);
            mIBLocal = mView.findViewById(R.id.toolbar_local);
            mIBHistory = mView.findViewById(R.id.toolbar_history);
            mIBDownload = mView.findViewById(R.id.toolbar_download);

            //将控件添加到toolbar
            addView(mView);
            setHideAndShow();
        }
    }

    public void initListener() {
        mIBMenuBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onIBMenuBackClickListener != null) {
                    onIBMenuBackClickListener.onClick();
                }
//                AppManage.getAppManager().finishActivity();
            }
        });
        mTVSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTVSearchClickListener != null) {
                    onTVSearchClickListener.onClick();
                }
            }
        });
        mIBLocal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onIBLocalClickListener != null) {
                    onIBLocalClickListener.onClick();
                }
            }
        });
        mIBHistory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onIBHistoryClickListener != null) {
                    onIBHistoryClickListener.onClick();
                }
            }
        });
        mIBDownload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onIBDownloadClickListener != null) {
                    onIBDownloadClickListener.onClick();
                }
            }
        });
        mETSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (onETSearchEditorActionListener != null) {
                    return onETSearchEditorActionListener.onEditorAction(v, actionId, event);
                }
                return false;
            }
        });
        mETSearch.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(onETSearchFocusChangeListener != null){
                    onETSearchFocusChangeListener.onFocusChange(v, hasFocus);
                }
            }
        });

        mETSearch.setOnEditTextKeyBackListener(new SafeEditText.OnEditTextKeyBackListener() {
            @Override
            public boolean onKeyBack() {
                if(onETSearchKeyBackListener != null){
                    return onETSearchKeyBackListener.onKeyBack();
                }
                // 这里根据自己项目的实际需要选择返回true或者false
                return false;
            }
        });

    }

    public SafeEditText getmETSearch() {
        return mETSearch;
    }

    // 中间EditText收起键盘
    public interface OnETSearchKeyBackListener {
        boolean onKeyBack();
    }

    private OnETSearchKeyBackListener onETSearchKeyBackListener;

    public void setOnETSearchKeyBackListener(OnETSearchKeyBackListener listener){
        onETSearchKeyBackListener = listener;
    }

    // 中间EditText点击事件
    public interface OnETSearchEditorActionListener {
        boolean onEditorAction(TextView v, int actionId, KeyEvent event);
    }

    private OnETSearchEditorActionListener onETSearchEditorActionListener;

    public void setOnETSearchEditorActionListener(OnETSearchEditorActionListener listener){
        onETSearchEditorActionListener = listener;
    }

    // 中间EditText获取焦点事件
    public interface OnETSearchFocusChangeListener {
        void onFocusChange(View v, boolean hasFocus);
    }

    private OnETSearchFocusChangeListener onETSearchFocusChangeListener;

    public void setOnETSearchFocusChangeListener(OnETSearchFocusChangeListener listener){
        onETSearchFocusChangeListener = listener;
    }

    /**
     * 让中间EditText失去焦点，并收起软键盘
     */
    public void setETSearchFocusChange(Context context) {
        mETSearch.clearFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(imm != null && imm.isActive()){
            imm.hideSoftInputFromWindow(mETSearch.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    // 左边IBMenuBack事件
    public interface OnIBMenuBackClickListener {
        void onClick();
    }

    private OnIBMenuBackClickListener onIBMenuBackClickListener;

    public void setOnIBMenuBackClickListener(OnIBMenuBackClickListener listener) {
        onIBMenuBackClickListener = listener;
    }

    // 右边TVSearch事件
    public interface OnTVSearchClickListener {
        void onClick();
    }

    private OnTVSearchClickListener onTVSearchClickListener;

    public void setOnTVSearchClickListener(OnTVSearchClickListener listener) {
        onTVSearchClickListener = listener;
    }

    // 右边IBLocal事件
    public interface OnIBLocalClickListener {
        void onClick();
    }

    private OnIBLocalClickListener onIBLocalClickListener;

    public void setOnIBLocalClickListener(OnIBLocalClickListener listener) {
        onIBLocalClickListener = listener;
    }

    // 右边IBHistory事件
    public interface OnIBHistoryClickListener {
        void onClick();
    }

    private OnIBHistoryClickListener onIBHistoryClickListener;

    public void setOnIBHistoryClickListener(OnIBHistoryClickListener listener) {
        onIBHistoryClickListener = listener;
    }

    // 右边IBDownload事件
    public interface OnIBDownloadClickListener {
        void onClick();
    }

    private OnIBDownloadClickListener onIBDownloadClickListener;

    public void setOnIBDownloadClickListener(OnIBDownloadClickListener listener) {
        onIBDownloadClickListener = listener;
    }


    public void showMenuBack() {
        mIBMenuBack.setVisibility(View.VISIBLE);
        isShowMenuBack = true;
    }

    public void hideMenuBack() {
        mIBMenuBack.setVisibility(View.GONE);
        isShowMenuBack = false;
    }

    public void showSearchText() {
        mTVSearch.setVisibility(View.VISIBLE);
        isShowSearchText = true;
    }

    public void hideSearchText() {
        mTVSearch.setVisibility(View.GONE);
        isShowSearchText = false;
    }

    public void showToolBarController() {
        mLLController.setVisibility(View.VISIBLE);
        isShowToolBarController = true;
    }

    public void hideToolBarController() {
        mLLController.setVisibility(View.GONE);
        isShowToolBarController = false;
    }

    private void setHideAndShow() {
        /**
         * 左边返回按钮图片
         */
        if (isShowMenuBack) {
            showMenuBack();
        } else {
            hideMenuBack();
        }
        /**
         * 右边“搜索”文字
         */
        if (isShowSearchText) {
            showSearchText();
        } else {
            hideSearchText();
        }
        /**
         * 右边三组按钮图片（本地、历史、下载）是否显示
         */
        if (isShowToolBarController) {
            showToolBarController();
        } else {
            hideToolBarController();
        }
        /**
         * 中间EditText显示
         */
//        mETSearch.setHint(middleEditText);
        // 1.新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(middleEditText);
        // 2.新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen.size_level_three),false);
        // 3.附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 4.设置hint，一定要进行转换,否则属性会消失
        mETSearch.setHint(new SpannedString(ss));

    }


}
