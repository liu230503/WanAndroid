package org.lmy.open.wanandroid.core.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.adapter.ArticleAdapter;
import org.lmy.open.wanandroid.business.main.bean.BeanRespArticleList;
import org.lmy.open.wanandroid.core.base.OnItemClickListener;

/**********************************************************************
 *
 *
 * @类名 ArticleList
 * @包名 org.lmy.open.wanandroid.core.widget
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public class ArticleList extends LinearLayout implements OnItemClickListener {
    /**
     * 广告悬浮窗View
     */
    private View mArticleLayout;
    /**
     * 文章列表
     */
    private RecyclerView mRecyclerView;
    /**
     * 文章列表适配器
     */
    private ArticleAdapter mArticleAdapter;

    /**
     * 当前的页码
     */
    private int mNowPage = 0;
    /**
     * 上次最后一个item的位数
     */
    private int mCacheLastItem;
    /**
     * 总文章数
     */
    private int mTotalArtice = 0;
    /**
     * 监听器
     */
    private OnRecyclerViewListener mRecyclerViewListener;

    public ArticleList(Context context) {
        super(context);
        init(context);
    }

    public ArticleList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ArticleList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    private void init(Context context) {
        mArticleLayout = View.inflate(context, R.layout.layout_artice, this);
        mRecyclerView = mArticleLayout.findViewById(R.id.rcv_article);
        mArticleAdapter = new ArticleAdapter(context, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mArticleAdapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            private int mLastItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mLastItem + 1 == mArticleAdapter.getItemCount()) {
                    if (mArticleAdapter.getItemCount() == mTotalArtice) {
                        if (mRecyclerViewListener != null) {
                            mRecyclerViewListener.onPrompt("已经没有更多了！");
                        }
                    }
                    mArticleAdapter.changeMoreStatus(ArticleAdapter.LOADING_MORE);
                    if (mRecyclerViewListener != null) {
                        mRecyclerViewListener.loadMore(mNowPage + 1);
                    }
                }
                if (mArticleAdapter.getItemCount() <= 0) {
                    if (mRecyclerViewListener != null) {
                        mRecyclerViewListener.onShowToolButton();
                    }
                    return;
                }
                if (isSameRow(mLastItem)) {
                    return;
                }
                if (mCacheLastItem < mLastItem) {
                    if (mRecyclerViewListener != null) {
                        mRecyclerViewListener.onHideToolButton();
                    }
                } else if (mCacheLastItem > mLastItem) {
                    if (mRecyclerViewListener != null) {
                        mRecyclerViewListener.onShowToolButton();
                    }
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

    /**
     * 添加数据
     *
     * @param beanRespArticleList 数据
     * @param addFooter           添加到尾部
     */
    public void addItem(BeanRespArticleList beanRespArticleList, boolean addFooter) {
        if (beanRespArticleList == null) {
            return;
        }
        mNowPage = beanRespArticleList.getCurPage();
        mTotalArtice = beanRespArticleList.getTotal();
        if (addFooter) {
            mArticleAdapter.addFooterItem(beanRespArticleList.getDatas());
        } else {
            mArticleAdapter.addHeaderItem(beanRespArticleList.getDatas());
        }
    }

    /**
     * 注册监听器
     *
     * @param listener 监听器
     */
    public void registerListener(OnRecyclerViewListener listener) {
        mRecyclerViewListener = listener;
    }

    /**
     * 反注册监听器
     */
    public void unRegisterListener() {
        mRecyclerViewListener = null;
    }

    /**
     * 还原并加载文章数据
     */
    public void initAndLoadArticle() {
        mArticleAdapter.clear();
        mNowPage = 0;
        if (mRecyclerViewListener != null) {
            mRecyclerViewListener.loadMore(mNowPage);
        }
    }

    /**
     * 滑动到顶端
     */
    public void onScrollTop() {
        if (mArticleAdapter.getItemCount() > 0) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    /**
     * 可见度是否发生变化
     *
     * @param lastItem 最后一项
     * @return 结果
     */
    private boolean isSameRow(int lastItem) {
        return mCacheLastItem == lastItem;
    }

    @Override

    public void onItemClick(View view) {

    }

    @Override
    public void onItemLongClick(View view) {

    }


    /**
     * 文章列表监听器
     */
    public interface OnRecyclerViewListener {
        /**
         * 弹出提示
         *
         * @param message 提示信息
         */
        void onPrompt(String message);

        /**
         * 上拉加载
         *
         * @param page 页码
         */
        void loadMore(int page);

        /**
         * 显示工具按钮
         */
        void onShowToolButton();

        /**
         * 隐藏工具按钮
         */
        void onHideToolButton();
    }
}
