package org.lmy.open.wanandroid.business.main.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;

import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.bean.BeanRespArticleList;
import org.lmy.open.wanandroid.business.main.contract.ArticleContract;
import org.lmy.open.wanandroid.business.main.presenter.ArtickeKistPresenter;
import org.lmy.open.wanandroid.core.base.BaseMvpFragment;
import org.lmy.open.wanandroid.core.comment.CreatePresenter;
import org.lmy.open.wanandroid.core.widget.ArticleList;
import org.lmy.open.widgetlibrary.banner.BannerLayout;
import org.lmy.open.widgetlibrary.banner.BeanBanner;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 ArticleListFragment
 * @包名 org.lmy.open.wanandroid.business.main.fragment
 * @author lmy
 * @创建日期 2018/3/7
 ***********************************************************************/
@CreatePresenter(ArtickeKistPresenter.class)
public class ArticleListFragment extends BaseMvpFragment<ArticleListFragment, ArtickeKistPresenter> implements ArticleContract.IArticleView,
        MainFragment.ToolListener, ArticleList.OnRecyclerViewListener {
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
    private ArticleList mArticleListView;

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
        MainFragment.setToolListener(this);
    }

    @Override
    protected void getViews() {
        mRootView = findView(R.id.cl_root);
        mBannerLayout = findView(R.id.bm_banner);
        mRefreshLayout = findView(R.id.swipeRefreshLayout);
        mArticleListView = findView(R.id.arl_article);
    }

    @Override
    protected void setViewsValue() {
        mRefreshLayout.setColorSchemeColors(mContext.getResources().getColor(R.color.theme_color));
        getPresenter().loadBanner();
        getPresenter().loadArticle(0);
    }

    @Override
    protected void setListeners() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mArticleListView.initAndLoadArticle();
            }
        });
        mArticleListView.registerListener(this);
    }

    @Override
    protected void processClick(View v) {

    }

    @Override
    public void initBanner(List<BeanBanner> beanBanners) {
        mBannerLayout.setBeanBannerList(beanBanners)
                .setDefaultImageResId(R.mipmap.picture_error)
                .setDefaultImageDrawable(mContext.getDrawable(R.mipmap.picture_error))
                .setIndexColor(getResources().getColor(R.color.theme_color))
                .setIntervalTime(5)
                .setOnItemClickListener(new BannerLayout.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //Todo banner item点击事件处理
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
        mArticleListView.addItem(beanRespArticleList, true);
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
                mArticleListView.initAndLoadArticle();
            }
        }).setActionTextColor(mContext.getResources().getColor(R.color.theme_color)).show();
    }

    @Override
    public void loadMore(int page) {
        getPresenter().loadArticle(page);
    }

    @Override
    public void onShowToolButton() {
        if (isVisible()) {
            MainFragment.onShowToolButton();
        }
    }

    @Override
    public void onHideToolButton() {
        if (isVisible()) {
            MainFragment.onHideToolButton();
        }
    }

    @Override
    public void onScrollTop() {
        if (isVisible()) {
            mArticleListView.onScrollTop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mArticleListView.unRegisterListener();
    }
}
