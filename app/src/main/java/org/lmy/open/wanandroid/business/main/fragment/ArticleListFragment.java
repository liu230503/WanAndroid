package org.lmy.open.wanandroid.business.main.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import org.lmy.open.utillibrary.WaitForCalm;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.adapter.ArticleAdapter;
import org.lmy.open.wanandroid.business.main.bean.BeanRespArticleList;
import org.lmy.open.wanandroid.business.main.contract.ArticleContract;
import org.lmy.open.wanandroid.business.main.presenter.ArtickeKistPresenter;
import org.lmy.open.wanandroid.core.base.BaseMvpFragment;
import org.lmy.open.wanandroid.core.comment.CreatePresenter;
import org.lmy.open.widgetlibrary.banner.BannerLayout;
import org.lmy.open.widgetlibrary.banner.BeanBanner;

import java.util.List;

import retrofit2.http.GET;

/**********************************************************************
 *
 *
 * @类名 ArticleListFragment
 * @包名 org.lmy.open.wanandroid.business.main.fragment
 * @author lmy
 * @创建日期 2018/3/7
 ***********************************************************************/
@CreatePresenter(ArtickeKistPresenter.class)
public class ArticleListFragment extends BaseMvpFragment<ArticleListFragment, ArtickeKistPresenter> implements ArticleContract.ArticleView {
    /**
     * 根布局
     */
    private ConstraintLayout mRootView;
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
     * 上次最后一个item的位数
     */
    private int mCacheLastItem;
    /**
     * 创建自身实例
     *
     * @param bundle 参数列表
     * @return Main1Fragment
     */
    public static ArticleListFragment newInstance(Bundle bundle) {
        ArticleListFragment fragment = new ArticleListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_articlelist;
    }

    @Override
    protected void initData() {
        mArticleAdapter = new ArticleAdapter(mContext);
    }

    @Override
    protected void getViews() {
        mRootView = findView(R.id.cl_root);
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
        getPresenter().loadBanner();
        getPresenter().loadArticle(mNowPage);
    }

    @Override
    protected void setListeners() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initAndLoadArticle();
            }
        });

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            private int mLastItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mLastItem + 1 == mArticleAdapter.getItemCount()) {
                    mArticleAdapter.changeMoreStatus(ArticleAdapter.LOADING_MORE);
                    getPresenter().loadArticle(mNowPage + 1);
                }
                if (mArticleAdapter.getItemCount() <= 0) {
                    MainFragment.onShowToolButton();
                    return;
                }
                if (isSameRow(mLastItem)) {
                    return;
                }
                if (mCacheLastItem < mLastItem) {
                    MainFragment.onHideToolButton();
                } else if (mCacheLastItem > mLastItem) {
                    MainFragment.onShowToolButton();
                }

                mCacheLastItem = mLastItem;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                mLastItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    protected void processClick(View v) {

    }

    private boolean isSameRow(int lastItem) {
        return mCacheLastItem == lastItem;
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

    @Override
    public void onPrompt(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Snackbar.make(mRootView, message, Snackbar.LENGTH_LONG).setAction("点我重试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().loadBanner();
                initAndLoadArticle();
            }
        }).setActionTextColor(mContext.getResources().getColor(R.color.theme_color)).show();
    }

    /**
     * 还原并加载文章数据
     */
    private void initAndLoadArticle() {
        mArticleAdapter.clear();
        mNowPage = 0;
        getPresenter().loadArticle(mNowPage);
    }
}
