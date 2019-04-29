package com.licj.apphub.commons.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * Created by licj on 2018/3/10.
 */

public class CountDownTimerUtils extends CountDownTimer {

    // 倒计时时间
    private TextView textView;

    public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
    }

    // 被点击之后触发
    @Override
    public void onTick(long millisUntilFinished) {
        textView.setClickable(false);
        textView.setText(millisUntilFinished / 1000 + "秒后可重试");
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(foregroundColorSpan, 0, 0, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }

    @Override
    public void onFinish() {
        textView.setText("重新获取验证码");
        textView.setClickable(true);// 重新获得点击
    }
}
