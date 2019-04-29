package com.licj.apphub.user;

import android.os.Bundle;

import com.licj.apphub.R;
import com.licj.apphub.commons.fragment.MyFragment;
import com.licj.apphub.follow.PFollowFragment;

public class UserFragment extends MyFragment<PUserFragment> {

    public UserFragment() {
    }

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
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
        return R.layout.fragment_user;
    }

    @Override
    public PUserFragment newP() {
        return new PUserFragment(context);
    }

}
