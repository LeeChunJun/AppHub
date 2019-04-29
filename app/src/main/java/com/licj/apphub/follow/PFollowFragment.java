package com.licj.apphub.follow;

import android.app.Activity;

import com.licj.apphub.commons.mvp.XPresent;
import com.licj.apphub.find.FindFragment;

public class PFollowFragment extends XPresent<FollowFragment> {

    public Activity context;

    public PFollowFragment() {
    }

    public PFollowFragment(Activity context){
        this.context = context;
    }
}
