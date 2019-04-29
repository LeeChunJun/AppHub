package com.licj.apphub.index;

import android.app.Activity;

import com.licj.apphub.commons.mvp.XPresent;

public class PIndexFragment extends XPresent<IndexFragment> {

    public Activity context;

    public PIndexFragment() {
    }

    public PIndexFragment(Activity context){
        this.context = context;
    }
}
