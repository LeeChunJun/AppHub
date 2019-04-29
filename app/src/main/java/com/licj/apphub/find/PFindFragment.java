package com.licj.apphub.find;

import android.app.Activity;

import com.licj.apphub.commons.mvp.XPresent;
import com.licj.apphub.index.IndexFragment;

public class PFindFragment extends XPresent<FindFragment> {

    public Activity context;

    public PFindFragment() {
    }

    public PFindFragment(Activity context){
        this.context = context;
    }
}
