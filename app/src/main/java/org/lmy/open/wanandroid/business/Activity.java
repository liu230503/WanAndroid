package org.lmy.open.wanandroid.business;

import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.core.base.BaseFragmentActivity;
import org.lmy.open.wanandroid.core.fhelp.FragmentPageManager;

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
        FragmentPageManager.getInstance().onStartMainFragment(null, null);
    }

    @Override
    protected void setListeners() {

    }
}
