package com.cf.hodgepodge.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cf.hodgepodge.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by cf on 2018/1/19.
 */

public class HomeVpAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> list;
    private String[] titles;

    public HomeVpAdapter(FragmentManager fm, List<BaseFragment> list, String[] titles) {
        super(fm);
        this.list = list;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
