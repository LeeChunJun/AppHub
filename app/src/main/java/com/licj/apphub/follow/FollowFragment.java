package com.licj.apphub.follow;

import android.os.Bundle;

import com.licj.apphub.R;
import com.licj.apphub.commons.fragment.MyFragment;
import com.licj.apphub.find.PFindFragment;

public class FollowFragment extends MyFragment<PFollowFragment> {

    public FollowFragment() {
    }

    public static FollowFragment newInstance() {
        FollowFragment fragment = new FollowFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_follow;
    }

    @Override
    public PFollowFragment newP() {
        return new PFollowFragment(context);
    }

}
