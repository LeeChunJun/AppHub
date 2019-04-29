package com.licj.apphub.commons.initial;

import android.os.Bundle;

import com.licj.apphub.commons.activity.MyActivity;
import com.licj.apphub.MainActivity;
import com.licj.apphub.R;
import com.licj.apphub.commons.mvp.Router;
import com.licj.apphub.commons.utils.AppUtils;

import java.util.Timer;
import java.util.TimerTask;

public class InitialActivity extends MyActivity {

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        AppUtils.hideNavigationBarStatusBar(context, true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_initial;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    protected void onStart() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Router.newIntent(context).to(MainActivity.class).launch();
                finish();
            }
        }, 0 * 1000L);

        super.onStart();
    }
}
