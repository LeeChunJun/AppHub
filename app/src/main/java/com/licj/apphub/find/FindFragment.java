package com.licj.apphub.find;

import android.os.Bundle;

import com.licj.apphub.R;
import com.licj.apphub.commons.fragment.MyFragment;
import com.licj.apphub.index.PIndexFragment;

public class FindFragment extends MyFragment<PFindFragment> {

    public FindFragment() {
    }

    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
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
        return R.layout.fragment_find;
    }

    @Override
    public PFindFragment newP() {
        return new PFindFragment(context);
    }

}
