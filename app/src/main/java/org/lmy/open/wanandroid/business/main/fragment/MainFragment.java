package org.lmy.open.wanandroid.business.main.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.adapter.PagerFragmentAdapter;
import org.lmy.open.wanandroid.business.splash.SplashFragment;
import org.lmy.open.wanandroid.core.base.BaseFragment;
import org.lmy.open.wanandroid.core.widget.SplashLogView;

import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 *
 *
 * @类名 MainFragment
 * @包名 org.lmy.open.wanandroid.business.main.fragment
 * @author lmy
 * @创建日期 2018/3/7
 ***********************************************************************/
public class MainFragment extends BaseFragment implements Handler.Callback, NavigationView.OnNavigationItemSelectedListener {
    /**
     * 切换布局消息
     */
    private static final int HANDLER_MESSAGE_SWITCH_LAYOUT = 10001;
    /**
     * 延迟时间
     */
    private static final long DELAY_TIME = 300;
    /**
     * SplashLogView
     */
    private SplashLogView mSplashLogView;
    /**
     * 根布局
     */
    private LinearLayout mRootView;
    /**
     * 主页面布局
     */
    private DrawerLayout mMainLayout;
    /**
     * 内容布局
     */
    private CoordinatorLayout mContentLayout;
    /**
     * title布局
     */
    private TabLayout mTitleLayout;
    /**
     * 页面加载器
     */
    private ViewPager mPagerView;
    /**
     * 工具按钮
     */
    private FloatingActionButton mToolButton;
    /**
     * 导航布局
     */
    private NavigationView mNavigationView;
    /**
     * 可切换页面
     */
    private List<Fragment> mPagers;
    /**
     * 标题
     */
    private List<String> mTitles;
    /**
     * viewPager适配器
     */
    private PagerFragmentAdapter mFragmentAdapter;
    /**
     * 执行动画的线程
     */
    private Handler mAnimHandler;
    /**
     * Toolbar
     */
    private Toolbar mToolbar;

    /**
     * 生成自身实例
     *
     * @param bundle 参数列表
     * @return MainFragment
     */
    public static MainFragment newInstance(Bundle bundle) {
        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {
        mPagers = new ArrayList<>();
        mPagers.add(new ArticleListFragment());
        mPagers.add(new SplashFragment());
        mTitles = new ArrayList<>();
        mTitles.add(mContext.getResources().getString(R.string.main_title_fine_articles));
        mTitles.add(mContext.getResources().getString(R.string.main_title_knowledge_system));
        mFragmentAdapter = new PagerFragmentAdapter(getFragmentManager(), mPagers, mTitles);
        mAnimHandler = new Handler(this);
    }

    @Override
    protected void getViews() {
        mSplashLogView = findView(R.id.splash);
        mRootView = findView(R.id.cl_root);
        mMainLayout = findView(R.id.dl_main_root);
        mContentLayout = findView(R.id.cl_main_content);
        mTitleLayout = findView(R.id.tb_title);
        mPagerView = findView(R.id.vp_pager);
        mToolButton = findView(R.id.fb_tool);
        mNavigationView = findView(R.id.nv_navigation);
        mToolbar = findView(R.id.tb_toolbar);
    }

    @Override
    protected void setViewsValue() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), mMainLayout, mToolbar, R.string.open, R.string.close);
        mMainLayout.addDrawerListener(toggle);
        toggle.syncState();
        mPagerView.setAdapter(mFragmentAdapter);
        mTitleLayout.setupWithViewPager(mPagerView);
        mAnimHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_SWITCH_LAYOUT, DELAY_TIME);
        mSplashLogView.startAnim();
    }

    @Override
    protected void setListeners() {
        setClick(mToolButton);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void processClick(View v) {
        switch (v.getId()) {
            case R.id.fb_tool:
                break;
            default:
                break;
        }
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case HANDLER_MESSAGE_SWITCH_LAYOUT:
                mRootView.removeView(mSplashLogView);
                Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.narrow);
                mMainLayout.startAnimation(animation);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mMainLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (mMainLayout.isDrawerOpen(GravityCompat.START)) {
            mMainLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
