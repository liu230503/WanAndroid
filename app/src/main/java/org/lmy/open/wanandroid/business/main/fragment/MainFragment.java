package org.lmy.open.wanandroid.business.main.fragment;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.os.Build;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.lmy.open.utillibrary.WaitForCalm;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.adapter.PagerFragmentAdapter;
import org.lmy.open.wanandroid.business.splash.SplashFragment;
import org.lmy.open.wanandroid.core.base.BaseFragment;
import org.lmy.open.wanandroid.core.fhelp.FragmentPageManager;
import org.lmy.open.wanandroid.core.widget.SplashLogView;
import org.lmy.open.widgetlibrary.CustomHead;

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
     * 是否已登陆
     */
    public static final String KEY_PREFERENCE_ISLOGIN = "isLogin";
    /**
     * 停止滑动后恢复toolButton时长
     */
    private static final long WAIT_TIME = 5 * 1000;
    /**
     * 切换布局消息
     */
    private static final int HANDLER_MESSAGE_SWITCH_LAYOUT = 10001;
    /**
     * 显示
     */
    private static final int HANDLER_MESSAGE_SHOW = 10002;

    /**
     * 隐藏
     */
    private static final int HANDLER_MESSAGE_HIDE = 10003;
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
    private static FloatingActionButton mToolButton;
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
    private static Handler mAnimHandler;
    /**
     * Toolbar
     */
    private Toolbar mToolbar;
    /**
     * 侧滑菜单头像
     */
    private CustomHead mNavigationHeader;
    /**
     * 侧滑菜单头部布局
     */
    private View mHeadLayout;
    /**
     * 已经登陆布局
     */
    private RelativeLayout mLoggedLayout;

    /**
     * 未登陆布局
     */
    private RelativeLayout mNotLoggedLayout;
    /**
     * 用户昵称
     */
    private TextView mUserNameView;
    /**
     * 登陆按钮
     */
    private Button mLoginBtn;
    /**
     * 隐藏工具栏按钮动画
     */
    private static AnimatorSet mHideToolBtnAnim;
    /**
     * 显示工具栏按钮动画
     */
    private static AnimatorSet mShowToolBtnAnim;

    /**
     * 等时任务
     */
    private static WaitForCalm mWaitForCalm;

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

    @SuppressLint("ResourceType")
    @Override
    protected void initData() {
        mPagers = new ArrayList<>();
        mPagers.add(new ArticleListFragment());
        mPagers.add(new SplashFragment());
        mTitles = new ArrayList<>();
        mTitles.add(mContext.getResources().getString(R.string.main_title_fine_articles));
        mTitles.add(mContext.getResources().getString(R.string.main_title_knowledge_system));
        mFragmentAdapter = new PagerFragmentAdapter(getFragmentManager(), mPagers, mTitles);
        mHideToolBtnAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.anim.scroll_hide_fab);
        mShowToolBtnAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.anim.scroll_show_fab);
        mAnimHandler = new Handler(this);
        mWaitForCalm = new WaitForCalm(WAIT_TIME);
        mWaitForCalm.setOnJobListener(new WaitForCalm.OnJobListener() {
            @Override
            public void onJob() {
                onShowToolButton();
            }
        });
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
        mHeadLayout = mNavigationView.getHeaderView(0);
        mLoggedLayout = mHeadLayout.findViewById(R.id.rl_logged);
        mNotLoggedLayout = mHeadLayout.findViewById(R.id.rl_not_logged);
        mNavigationHeader = mHeadLayout.findViewById(R.id.ch_head);
        mUserNameView = mHeadLayout.findViewById(R.id.tv_userName);
        mLoginBtn = mHeadLayout.findViewById(R.id.btn_login);
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
        mHideToolBtnAnim.setTarget(mToolButton);
        mShowToolBtnAnim.setTarget(mToolButton);
        if (mSpfUtil.getBoolean(KEY_PREFERENCE_ISLOGIN, false)) {
            mLoggedLayout.setVisibility(View.VISIBLE);
            mNotLoggedLayout.setVisibility(View.GONE);
            mNavigationHeader.setImageResource(R.mipmap.wetalk_default);
            mUserNameView.setText("用户名");
        } else {
            mLoggedLayout.setVisibility(View.GONE);
            mNotLoggedLayout.setVisibility(View.VISIBLE);
        }
        mAnimHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_SWITCH_LAYOUT, DELAY_TIME);
        mSplashLogView.startAnim();
    }

    @Override
    protected void setListeners() {
        setClick(mToolButton);
        setClick(mLoginBtn);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void processClick(View v) {
        switch (v.getId()) {
            case R.id.fb_tool:
                break;
            case R.id.btn_login:
                mMainLayout.closeDrawer(GravityCompat.START);
                FragmentPageManager.getInstance().onStartLoginFragment(null, null);
                break;
            default:
                break;
        }
    }

    /**
     * 隐藏工具按钮
     */
    public static void onHideToolButton() {
        mWaitForCalm.activity();
        if (mToolButton.getVisibility() == View.VISIBLE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mAnimHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_HIDE, mHideToolBtnAnim.getTotalDuration());
            } else {
                mAnimHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_HIDE, 600);
            }
            mHideToolBtnAnim.start();
        }
    }

    /**
     * 显示工具按钮
     */
    public static void onShowToolButton() {
        if (mToolButton.getVisibility() == View.GONE || mToolButton.getVisibility() == View.INVISIBLE) {
            mAnimHandler.sendEmptyMessage(HANDLER_MESSAGE_SHOW);
            mShowToolBtnAnim.start();
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
            case HANDLER_MESSAGE_SHOW:
                mToolButton.setVisibility(View.VISIBLE);
                break;
            case HANDLER_MESSAGE_HIDE:
                mToolButton.setVisibility(View.GONE);
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentPageManager pageManager = FragmentPageManager.getInstance();
        switch (item.getItemId()) {
            case R.id.navigation_item_personal:
                pageManager.onStartPersonalFragment(null, null);
                break;
            case R.id.navigation_item_collection:
                pageManager.onStartCollectionFragment(null, null);
                break;
            case R.id.navigation_item_letter:
                pageManager.onStartLetterFragment(null, null);
                break;
            case R.id.navigation_item_common:
                pageManager.onStartCommonFragment(null, null);
                break;
            case R.id.navigation_item_setting:
                pageManager.onStartSettingFragment(null, null);
                break;
            case R.id.navigation_item_about:
                pageManager.onStartAboutFragment(null, null);
                break;
            default:
                break;
        }
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
