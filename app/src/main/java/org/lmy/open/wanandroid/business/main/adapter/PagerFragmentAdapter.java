package org.lmy.open.wanandroid.business.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 PagerFragmentAdapter
 * @包名 org.lmy.open.wanandroid.business.main.adapter
 * @author lmy
 * @创建日期 2018/3/7
 ***********************************************************************/
public class PagerFragmentAdapter extends FragmentPagerAdapter {
    /**
     * Fragment列表
     */
    private List<Fragment> mFragments;
    /**
     * Title列表
     */
    private List<String> mTitles;

    public PagerFragmentAdapter(FragmentManager fragmentManager, List<Fragment> fragments, List<String> titles) {
        super(fragmentManager);
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments == null) {
            return null;
        }
        if (mFragments.size() <= 0) {
            return null;
        }
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        if (mFragments == null) {
            return 0;
        }
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles == null) {
            return null;
        }
        if (mTitles.size() <= 0) {
            return null;
        }
        return mTitles.get(position % mTitles.size());
    }
}
