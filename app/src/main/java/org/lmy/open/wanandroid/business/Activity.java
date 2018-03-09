package org.lmy.open.wanandroid.business;

import android.os.Bundle;

import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.core.base.BaseFragmentActivity;
import org.lmy.open.wanandroid.core.fhelp.AnimationsFactory;
import org.lmy.open.wanandroid.core.fhelp.FragmentPageManager;

import static org.lmy.open.wanandroid.business.main.fragment.MainFragment.KEY_BUNDLE_PAGE_NUM;

/**********************************************************************
 *
 *
 * @类名 Activity
 * @包名 org.lmy.open.wanandroid.business.main.activity
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
public class Activity extends BaseFragmentActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initData() {
        FragmentPageManager.getInstance().init(mContext, R.id.contentFrame, getSupportFragmentManager());
    }

    @Override
    protected void getViews() {

    }

    @Override
    protected void setViewsValue() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_BUNDLE_PAGE_NUM, 0);
        FragmentPageManager.getInstance().onStartMainFragment(bundle, AnimationsFactory.getInstance().getDefaultStartMainAnim());
    }

    @Override
    protected void setListeners() {

    }
}
