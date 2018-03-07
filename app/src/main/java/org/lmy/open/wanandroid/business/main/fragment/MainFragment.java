package org.lmy.open.wanandroid.business.main.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.adapter.ArticleAdapter;
import org.lmy.open.wanandroid.business.main.bean.BeanRespArticleList;
import org.lmy.open.wanandroid.business.main.contract.MainContract;
import org.lmy.open.wanandroid.business.main.presenter.MainPresenter;
import org.lmy.open.wanandroid.core.base.BaseMvpFragment;
import org.lmy.open.wanandroid.core.comment.CreatePresenter;
import org.lmy.open.wanandroid.core.widget.SplashLogView;
import org.lmy.open.widgetlibrary.banner.BannerLayout;
import org.lmy.open.widgetlibrary.banner.BeanBanner;

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
    /**
     * 轮播组件
     */
    private BannerLayout mBannerLayout;
    /**
     * 下啦刷新布局
     */
    private SwipeRefreshLayout mRefreshLayout;
    /**
     * 文章列表
     */
    private RecyclerView mRecyclerView;
    /**
     * 文章列表适配器
     */
    private ArticleAdapter mArticleAdapter;
    /**
     * LinearLayoutManager
     */
    private LinearLayoutManager mLinearLayoutManager;
    /**
     * 当前的页码
     */
    private int mNowPage = 0;

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
        mArticleAdapter = new ArticleAdapter(mContext);
    }

    @Override
    protected void getViews() {
        mSplashLogView = findView(R.id.splash);
        mRootView = findView(R.id.view_root);
        mMainLayout = findView(R.id.lly_main);
        mBannerLayout = findView(R.id.bm_banner);
        mRefreshLayout = findView(R.id.swipeRefreshLayout);
        mRecyclerView = findView(R.id.rcv_article);
    }

    @Override
    protected void setViewsValue() {
        mRefreshLayout.setColorSchemeColors(mContext.getResources().getColor(R.color.theme_color));
        mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mArticleAdapter);
        getPresenter().onShowLogoAnim();
        getPresenter().loadBanner();
        getPresenter().loadArticle(mNowPage);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void setListeners() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mNowPage = 0;
                mArticleAdapter.clear();
                getPresenter().loadArticle(mNowPage);
            }
        });

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastItem + 1 == mArticleAdapter.getItemCount()) {
                    mArticleAdapter.changeMoreStatus(ArticleAdapter.LOADING_MORE);
                    getPresenter().loadArticle(mNowPage + 1);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                lastItem = layoutManager.findLastVisibleItemPosition();
            }
        });
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
                .setIndexColor(getResources().getColor(R.color.theme_color))
                .setIntervalTime(5)
                .setOnItemClickListener(new BannerLayout.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Log.e("landptf", "position = " + position);
                    }
                })
                .show();
    }

    @Override
    public void showLoadAnim() {
        if (mRefreshLayout.isRefreshing()) {
            return;
        }
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void closeLoadAnim() {
        if (!mRefreshLayout.isRefreshing()) {
            return;
        }
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void initArticleList(BeanRespArticleList beanRespArticleList) {
        if (beanRespArticleList == null) {
            return;
        }
        mNowPage = beanRespArticleList.getCurPage();
        mArticleAdapter.addFooterItem(beanRespArticleList.getDatas());
    }
}
