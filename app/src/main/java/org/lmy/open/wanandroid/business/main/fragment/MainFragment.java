package org.lmy.open.wanandroid.business.main.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.contract.MainContract;
import org.lmy.open.wanandroid.business.main.presenter.MainPresenter;
import org.lmy.open.wanandroid.core.application.WanAndroidApp;
import org.lmy.open.wanandroid.core.base.BaseMvpFragment;
import org.lmy.open.wanandroid.core.comment.CreatePresenter;
import org.lmy.open.wanandroid.core.widget.SplashLogView;
import org.lmy.open.widgetlibrary.banner.BannerLayout;
import org.lmy.open.widgetlibrary.banner.BeanBanner;

import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 *
 *
 * @类名 MainFragment
 * @包名 org.lmy.open.wanandroid.business.main
 * @author lmy
 * @创建日期 2018/2/27
 ***********************************************************************/
@CreatePresenter(MainPresenter.class)
public class MainFragment extends BaseMvpFragment<MainFragment, MainPresenter> implements MainContract.MainIView {
    /**
     * SplashLogView
     */
    private SplashLogView mSplashLogView;
    /**
     * 根布局
     */
    private ConstraintLayout mRootView;
    /**
     * 主画面
     */
    private LinearLayout mMainLayout;

    private BannerLayout mBannerLayout;

    /**
     * 创建自身实例
     *
     * @param bundle 参数列表
     * @return MainFragment
     */
    public static MainFragment newInstance(Bundle bundle) {
        MainFragment fragment = new MainFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getViews() {
        mSplashLogView = findView(R.id.splash);
        mRootView = findView(R.id.view_root);
        mMainLayout = findView(R.id.lly_main);
        mBannerLayout = findView(R.id.bm_banner);
    }

    @Override
    protected void setViewsValue() {
        getPresenter().onShowLogoAnim();
        getPresenter().loadBanner();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void processClick(View v) {

    }

    @Override
    public void onShowMainLayout() {
        mRootView.removeView(mSplashLogView);
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.narrow);
        mMainLayout.startAnimation(animation);
    }

    @Override
    public SplashLogView getSplashLogView() {
        return mSplashLogView;
    }

    @Override
    public ConstraintLayout getRootLayout() {
        return mRootView;
    }

    @Override
    public LinearLayout getMainLayout() {
        return mMainLayout;
    }

    @Override
    public void initBanner(List<BeanBanner> beanBanners) {
        mBannerLayout.setBeanBannerList(beanBanners)
                .setDefaultImageResId(R.mipmap.picture_error)
                .setIndexPosition(BannerLayout.INDEX_POSITION_BOTTOM)
                .setIndexColor(getResources().getColor(R.color.theme_color))
                .setIntervalTime(3)
                .setOnItemClickListener(new BannerLayout.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Log.e("landptf", "position = " + position);
                    }
                })
                .show();
    }
}
