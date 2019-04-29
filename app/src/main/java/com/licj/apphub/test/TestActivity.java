package com.licj.apphub.test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.licj.apphub.R;
import com.licj.apphub.commons.utils.KeyboardLayout;

public class TestActivity extends AppCompatActivity implements KeyboardLayout.KeyboardLayoutListener{
    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        EditText editText = findViewById(R.id.editText);
//        editText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            // 当键盘弹出隐藏的时候会调用此方法。
//            @Override
//            public void onGlobalLayout() {
//                final Rect rect = new Rect();
//                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
//                final int screenHeight = getWindow().getDecorView().getRootView().getHeight();
//                Log.e(TAG, rect.bottom + "#" + screenHeight);
//                final int heightDifference = screenHeight - rect.bottom;
//                boolean visible = heightDifference > screenHeight / 3;
//                if (visible) {
//                    Log.d(TAG, "软键盘显示");
//                } else {
//                    Log.d(TAG, "软键盘隐藏");
//                }
//            }
//        });


        KeyboardLayout keyboardLayout = new KeyboardLayout(this, editText);
        keyboardLayout.setKeyboardListener(this);

    }

    @Override
    public void onKeyboardStateChanged(boolean isActive, int keyboardHeight) {
        if (isActive) {
            Log.d(TAG, "软键盘显示");
        } else {
            Log.d(TAG, "软键盘隐藏");
        }
    }

}
