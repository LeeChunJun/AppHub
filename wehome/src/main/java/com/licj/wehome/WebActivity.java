package com.licj.wehome;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WebActivity extends AppCompatActivity {

    private com.tencent.smtt.sdk.WebView tencent_webview;
    private String url = "file:///android_asset/show.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
        tencent_webview = findViewById(R.id.web);

        WebSettings webSettings = tencent_webview.getSettings();
        // 如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        // 支持插件
        webSettings.setPluginsEnabled(true);
        webSettings.setBlockNetworkImage(false);// 解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // 解决跨域问题
        try {
            Class<?> clazz = webSettings.getClass();
            Method method = clazz.getMethod(
                    "setAllowUniversalAccessFromFileURLs", boolean.class);
            if (method != null) {
                method.invoke(webSettings, true);
            }
        } catch (IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        tencent_webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

        });

        String homeUrl = getIntent().getStringExtra("HOME_URL");
        if (homeUrl.equals("")) {
            tencent_webview.loadUrl(url);
        } else {
            tencent_webview.loadUrl(homeUrl);
        }
    }

}
