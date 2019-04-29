package com.licj.apphub.user;

import android.app.Activity;

import com.licj.apphub.commons.mvp.XPresent;
import com.licj.apphub.follow.FollowFragment;

public class PUserFragment extends XPresent<UserFragment> {

    public Activity context;

    public PUserFragment() {
    }

    public PUserFragment(Activity context){
        this.context = context;
    }
}
