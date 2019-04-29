package com.licj.apphub;

import android.app.Activity;

import com.licj.apphub.commons.mvp.XPresent;

public class PMain extends XPresent<MainActivity> {

    public Activity context;

    public PMain() {
    }

    public PMain(Activity context) {
        this.context = context;
    }
}
