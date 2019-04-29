package com.licj.apphub;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.licj.apphub.commons.activity.MyActivity;
import com.licj.apphub.commons.utils.AppUtils;
import com.licj.apphub.find.FindFragment;
import com.licj.apphub.follow.FollowFragment;
import com.licj.apphub.index.IndexFragment;
import com.licj.apphub.user.UserFragment;

import java.lang.reflect.Field;

import butterknife.BindView;

public class MainActivity extends MyActivity<PMain> implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.btn_nav)
    BottomNavigationView btnNav;

    private IndexFragment mIndexFragment;
    private FindFragment mFindFragment;
    private FollowFragment mFollowFragment;
    private UserFragment mUserFragment;

    @Override
    public void initData(Bundle savedInstanceState) {
        disableShiftMode(btnNav);
        btnNav.setItemIconTintList(null);
        btnNav.setOnNavigationItemSelectedListener(this);
        onTabSelected(0);
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        AppUtils.statusBarTintColor(context, getResources().getColor(R.color.white));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public PMain newP() {
        return new PMain(context);
    }

    @Override
    public void onBackPressed() {
        if(mIndexFragment == null || !mIndexFragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        switch (itemId) {
            case R.id.item_index:
                onTabSelected(0);
                break;
            case R.id.item_find:
                onTabSelected(1);
                break;
            case R.id.item_follow:
                onTabSelected(2);
                break;
            case R.id.item_user:
                onTabSelected(3);
                break;
        }
        return true;
    }

    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (mIndexFragment == null) {
                    mIndexFragment = IndexFragment.newInstance();
                    transaction.add(R.id.ll_content, mIndexFragment);
                } else {
                    transaction.show(mIndexFragment);
                }
                break;
            case 1:
                if (mFindFragment == null) {
                    mFindFragment = FindFragment.newInstance();
                    transaction.add(R.id.ll_content, mFindFragment);
                } else {
                    transaction.show(mFindFragment);
                }
                break;
            case 2:
                if (mFollowFragment == null) {
                    mFollowFragment = FollowFragment.newInstance();
                    transaction.add(R.id.ll_content, mFollowFragment);
                } else {
                    transaction.show(mFollowFragment);
                }
                break;
            case 3:
                if (mUserFragment == null) {
                    mUserFragment = UserFragment.newInstance();
                    transaction.add(R.id.ll_content, mUserFragment);
                } else {
                    transaction.show(mUserFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mIndexFragment != null) {
            transaction.hide(mIndexFragment);
        }
        if (mFindFragment != null) {
            transaction.hide(mFindFragment);
        }
        if (mFollowFragment != null) {
            transaction.hide(mFollowFragment);
        }
        if (mUserFragment != null) {
            transaction.hide(mUserFragment);
        }
    }

    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView navigationView) {

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("labelVisibilityMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setInt(menuView, LabelVisibilityMode.LABEL_VISIBILITY_LABELED);// labelVisibilityMode
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShifting(false);
                item.setChecked(item.getItemData().isChecked());
            }

        } catch (NoSuchFieldException e) {
            Log.e(TAG, "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Unable to change value of shift mode", e);
        }
    }

}
