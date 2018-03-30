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

import org.lmy.open.netlibrary.internet.api.ISendRequest;
import org.lmy.open.netlibrary.internet.api.RequestProxy;
import org.lmy.open.utillibrary.LogHelper;
import org.lmy.open.utillibrary.WaitForCalm;
import org.lmy.open.utillibrary.imageload.EnumImage;
import org.lmy.open.utillibrary.imageload.LoadImageProxy;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.adapter.PagerFragmentAdapter;
import org.lmy.open.wanandroid.core.application.WanAndroidApp;
import org.lmy.open.wanandroid.core.base.BaseFragment;
import org.lmy.open.wanandroid.core.fhelp.FragmentPageManager;
import org.lmy.open.wanandroid.core.widget.SplashLogView;
import org.lmy.open.widgetlibrary.CustomHead;

import java.util.ArrayList;
import java.util.List;

import static org.lmy.open.wanandroid.business.login.fragment.LoginFragment.KEY_SPF_ICON;
import static org.lmy.open.wanandroid.business.login.fragment.LoginFragment.KEY_SPF_IS_LOGIN;
import static org.lmy.open.wanandroid.business.login.fragment.LoginFragment.KEY_SPF_USER_NAME;

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
     * 当前显示page的页码
     */
    public static final String KEY_BUNDLE_PAGE_NUM = "pageNumber";
    /**
     * 停止滑动后恢复toolButton时长
     */
    private static final long WAIT_TIME = 5 * 1000;
    /**
     * 自动消失时间
     */
    private static final long DISAPPEARANCE_TIME = 10 * 1000;
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
    private static ViewPager sPagerView;
    /**
     * 工具按钮
     */
    private static FloatingActionButton sToolButton;
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
    private static Handler sAnimHandler;
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
    private static AnimatorSet sHideToolBtnAnim;
    /**
     * 显示工具栏按钮动画
     */
    private static AnimatorSet sShowToolBtnAnim;

    /**
     * toolButton 下滑后自动恢复等时任务
     */
    private static WaitForCalm sWaitForCalm;
    /**
     * 工具列表布局
     */
    private static RelativeLayout sToolListLayout;
    /**
     * 置顶布局
     */
    private LinearLayout mTopLayout;
    /**
     * 搜索布局
     */
    private LinearLayout mSearchLayout;
    /**
     * 置顶布局动画
     */
    private AnimatorSet mTopLayoutAnim;
    /**
     * 搜索布局动画
     */
    private AnimatorSet mSearchLayoutAnim;
    /**
     * toolButton 弹出后恢复等时任务
     */
    private static WaitForCalm sToolLisrWaiter;
    /**
     * 工具栏监听
     */
    private static List<ToolListener> sToolListeners;
    /**
     * 页面变化监听
     */
    private static List<PageSelectedListener> sPageSelectedListeners;

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
        List<Fragment> fragments = getFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof ArticleListFragment) {
                    mPagers.add(fragment);
                } else if ((fragment instanceof HierarchyFragment)) {
                    mPagers.add(fragment);
                }
            }
            if (mPagers.size() <= 0) {
                mPagers.add(ArticleListFragment.newInstance(getArguments()));
                mPagers.add(HierarchyFragment.newInstance(getArguments()));
            }
        }
        mTitles = new ArrayList<>();
        mTitles.add(mContext.getResources().getString(R.string.main_title_fine_articles));
        mTitles.add(mContext.getResources().getString(R.string.main_title_knowledge_system));
        mFragmentAdapter = new PagerFragmentAdapter(getChildFragmentManager(), mPagers, mTitles);
        sHideToolBtnAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.anim.scroll_hide_fab);
        sShowToolBtnAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.anim.scroll_show_fab);
        mTopLayoutAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.anim.add_bill_anim);
        mSearchLayoutAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.anim.add_bill_anim);
        sAnimHandler = new Handler(this);
        sWaitForCalm = new WaitForCalm(WAIT_TIME);
        sWaitForCalm.setOnJobListener(new WaitForCalm.OnJobListener() {
            @Override
            public void onJob() {
                onShowToolButton();
            }
        });
        sToolLisrWaiter = new WaitForCalm(DISAPPEARANCE_TIME);
        sToolLisrWaiter.setOnJobListener(new WaitForCalm.OnJobListener() {
            @Override
            public void onJob() {
                onHideToolList();
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
        sPagerView = findView(R.id.vp_pager);
        sToolButton = findView(R.id.fb_tool);
        mNavigationView = findView(R.id.nv_navigation);
        mToolbar = findView(R.id.tb_toolbar);
        mHeadLayout = mNavigationView.getHeaderView(0);
        mLoggedLayout = mHeadLayout.findViewById(R.id.rl_logged);
        mNotLoggedLayout = mHeadLayout.findViewById(R.id.rl_not_logged);
        mNavigationHeader = mHeadLayout.findViewById(R.id.ch_head);
        mUserNameView = mHeadLayout.findViewById(R.id.tv_userName);
        mLoginBtn = mHeadLayout.findViewById(R.id.btn_login);
        mTopLayout = findView(R.id.lly_top);
        mSearchLayout = findView(R.id.lly_search);
        sToolListLayout = findView(R.id.rl_tool);
    }

    @Override
    protected void setViewsValue() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), mMainLayout, mToolbar, R.string.open, R.string.close);
        mMainLayout.addDrawerListener(toggle);
        toggle.syncState();
        sHideToolBtnAnim.setTarget(sToolButton);
        sShowToolBtnAnim.setTarget(sToolButton);
        if (mSpfUtil.getBoolean(KEY_SPF_IS_LOGIN, false)) {
            mLoggedLayout.setVisibility(View.VISIBLE);
            mNotLoggedLayout.setVisibility(View.GONE);
            LoadImageProxy.getInstance().loadImage(mNavigationHeader, mSpfUtil.getString(KEY_SPF_ICON), EnumImage.USER_ICON);
            mNavigationHeader.setImageResource(R.mipmap.wetalk_default);
            mUserNameView.setText(mSpfUtil.getString(KEY_SPF_USER_NAME));
        } else {
            mLoggedLayout.setVisibility(View.GONE);
            mNotLoggedLayout.setVisibility(View.VISIBLE);
        }
        sToolListLayout.setVisibility(View.GONE);
        if (WanAndroidApp.getInstance().isCanShowStartAnimation()) {
            WanAndroidApp.getInstance().setCanShowStartAnimation(false);
            mSplashLogView.setVisibility(View.VISIBLE);
            sAnimHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_SWITCH_LAYOUT, DELAY_TIME);
            mSplashLogView.startAnim();
        } else {
            mSplashLogView.setVisibility(View.GONE);
        }
        initPager();
    }

    @Override
    protected void setListeners() {
        setClick(sToolButton);
        setClick(mLoginBtn);
        setClick(mTopLayout);
        setClick(mSearchLayout);
        mNavigationView.setNavigationItemSelectedListener(this);
        sPagerView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (sPageSelectedListeners == null) {
                    return;
                }
//                for (PageSelectedListener listener : sPageSelectedListeners) {
//                    listener.onPageSelected(position);
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void processClick(View v) {
        switch (v.getId()) {
            case R.id.fb_tool:
                if (sToolListLayout.getVisibility() == View.GONE || sToolListLayout.getVisibility() == View.INVISIBLE) {
                    onShowToolList();
                } else {
                    onHideToolList();
                }
                break;
            case R.id.btn_login:
                mMainLayout.closeDrawer(GravityCompat.START);
                getArguments().putInt(KEY_BUNDLE_PAGE_NUM, sPagerView.getCurrentItem());
                FragmentPageManager.getInstance().onStartLoginFragment(getArguments(), null);
                break;
            case R.id.lly_top:
                onHideToolList();
                if (sToolListeners == null) {
                    return;
                }
                for (ToolListener listener : sToolListeners) {
                    listener.onScrollTop();
                }
                break;
            case R.id.lly_search:
                onHideToolList();
                break;
            default:
                break;
        }
    }

    /**
     * 初始化viewpager
     */
    private void initPager() {
        sPagerView.setAdapter(mFragmentAdapter);
        mTitleLayout.setupWithViewPager(sPagerView);
        int pageNum = getArguments().getInt(KEY_BUNDLE_PAGE_NUM, 0);
        // 解决viewpager 滑动到指定页面时有过度动画问题
//        try {
//            Field field = sPagerView.getClass().getDeclaredField("mCurItem");
//            field.setAccessible(true);
//            field.setInt(sPagerView, pageNum);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        mFragmentAdapter.notifyDataSetChanged();
        sPagerView.setCurrentItem(pageNum);
    }

    /**
     * 隐藏工具按钮
     */
    public static void onHideToolButton() {
        sWaitForCalm.activity();
        if (sToolButton.getVisibility() == View.VISIBLE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                sAnimHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_HIDE, sHideToolBtnAnim.getTotalDuration());
            } else {
                sAnimHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_HIDE, 600);
            }
            sHideToolBtnAnim.start();
            onHideToolList();
        }
    }

    /**
     * 显示工具按钮
     */
    public static void onShowToolButton() {
        if (sToolButton.getVisibility() == View.GONE || sToolButton.getVisibility() == View.INVISIBLE) {
            sAnimHandler.sendEmptyMessage(HANDLER_MESSAGE_SHOW);
            sShowToolBtnAnim.start();
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
                sToolButton.setVisibility(View.VISIBLE);
                break;
            case HANDLER_MESSAGE_HIDE:
                sToolButton.setVisibility(View.GONE);
                break;
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
                pageManager.onStartCollectionFragment(getArguments(), null);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        sWaitForCalm.destory();
        sWaitForCalm.setOnJobListener(null);
        sToolLisrWaiter.destory();
        sToolLisrWaiter.setOnJobListener(null);
    }

    /**
     * 显示工具列表
     */
    private void onShowToolList() {
        sToolListLayout.setVisibility(View.VISIBLE);
        mTopLayoutAnim.setTarget(mTopLayout);
        mTopLayoutAnim.setStartDelay(150);
        mTopLayoutAnim.start();
        mSearchLayoutAnim.setTarget(mSearchLayout);
        mSearchLayoutAnim.start();
        sToolLisrWaiter.activity();
    }

    /**
     * 隐藏工具列表
     */
    private static void onHideToolList() {
        sToolListLayout.setVisibility(View.INVISIBLE);
    }

    /**
     * 注册监听
     *
     * @param listener 监听器
     */
    public static void setToolListener(ToolListener listener) {
        if (sToolListeners == null) {
            sToolListeners = new ArrayList<>();
        }
        sToolListeners.add(listener);
    }

    /**
     * 注册监听
     *
     * @param listener 监听器
     */
    public static void setPageSelectedListener(PageSelectedListener listener) {
        if (sPageSelectedListeners == null) {
            sPageSelectedListeners = new ArrayList<>();
        }
        sPageSelectedListeners.add(listener);
    }

    /**
     * 工具栏监听
     */
    public interface ToolListener {
        /**
         * 滑动到顶端
         */
        void onScrollTop();
    }

    /**
     * viewpager页面切换监听
     */
    public interface PageSelectedListener {
        /**
         * 页面发生变化
         *
         * @param position 切换到的页面
         */
        void onPageSelected(int position);
    }

}
