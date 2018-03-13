package org.lmy.open.wanandroid.business.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.lmy.open.utillibrary.LogHelper;
import org.lmy.open.utillibrary.imageload.EnumImage;
import org.lmy.open.utillibrary.imageload.LoadImageProxy;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.bean.BeanRespArticle;
import org.lmy.open.wanandroid.core.base.BaseRecyclerAdapter;
import org.lmy.open.wanandroid.core.base.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static org.lmy.open.utillibrary.DateUtil.DEFAULT_DATE_FORMAT;
import static org.lmy.open.utillibrary.DateUtil.addDays;
import static org.lmy.open.utillibrary.DateUtil.getCurrentYear;
import static org.lmy.open.utillibrary.DateUtil.getDateTime;

/**********************************************************************
 *
 *
 * @类名 ArticleAdapter
 * @包名 org.lmy.open.wanandroid.business.main.adapter
 * @author lmy
 * @创建日期 2018/3/6
 ***********************************************************************/
public class ArticleAdapter extends BaseRecyclerAdapter {
    /**
     * item布局
     */
    private static final int TYPE_ITEM = 0;
    /**
     * 底部加载布局
     */
    private static final int TYPE_FOOTER = 1;
    /**
     * 上拉加载更多
     */
    public static final int PULL_UP_LOAD_MORE = 0;
    /**
     * 正在加载中
     */
    public static final int LOADING_MORE = 1;
    /**
     * 没有加载更多 隐藏
     */
    public static final int NO_LOAD_MORE = 2;

    /**
     * 上拉加载更多状态-默认为0
     */
    private int mLoadMoreStatus = PULL_UP_LOAD_MORE;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 布局
     */
    private LayoutInflater mInflater;
    /**
     * 数据源
     */
    private List<BeanRespArticle> mDatas;

    public ArticleAdapter(Context context, OnItemClickListener listener) {
        super(listener);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View itemView = mInflater.inflate(R.layout.layout_adapter_article_item, parent, false);
            onBindClickListener(itemView);
            return new ItemViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            View itemView = mInflater.inflate(R.layout.layout_load_more_footview, parent, false);
            return new FooterViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            if (position >= mDatas.size()) {
                return;
            }
            BeanRespArticle bean = mDatas.get(position);
            LoadImageProxy.getInstance().loadImage(itemViewHolder.mHeaderView, null, EnumImage.ARTICLE_HEADER);
            itemViewHolder.mNameView.setText(bean.getAuthor());
            itemViewHolder.mTimeView.setText(creatTime(bean.getPublishTime()));
            itemViewHolder.mTitleView.setText(bean.getTitle());
            itemViewHolder.mClassifView.setText(bean.getChapterName());
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            switch (mLoadMoreStatus) {
                case PULL_UP_LOAD_MORE:
                    footerViewHolder.mTvLoadText.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footerViewHolder.mTvLoadText.setText("正加载更多...");
                    break;
                case NO_LOAD_MORE:
                    footerViewHolder.mLoadLayout.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
//        if (mDatas.size() > 0) {
//            return mDatas.size() + 1;
//        } else {
            return mDatas.size();
//        }
    }

    @Override
    public void onItemClick(View view) {

    }

    @Override
    public void onItemLongClick(View view) {

    }

    /**
     * item
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        /**
         * 头像
         */
        private ImageView mHeaderView;
        /**
         * 昵称
         */
        private TextView mNameView;
        /**
         * 发布时间
         */
        private TextView mTimeView;
        /**
         * 标题
         */
        private TextView mTitleView;
        /**
         * 分类
         */
        private TextView mClassifView;
        /**
         * 点赞
         */
        private ImageView mHeartView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mHeaderView = itemView.findViewById(R.id.iv_header);
            mNameView = itemView.findViewById(R.id.tv_name);
            mTimeView = itemView.findViewById(R.id.tv_time);
            mTitleView = itemView.findViewById(R.id.tv_title);
            mClassifView = itemView.findViewById(R.id.tv_class);
            mHeartView = itemView.findViewById(R.id.iv_heart);
            mHeartView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    /**
     * 加载布局
     */
    public class FooterViewHolder extends RecyclerView.ViewHolder {
        /**
         * 进度条
         */
        private ProgressBar mPbLoad;
        /**
         * 文本框
         */
        private TextView mTvLoadText;
        /**
         * 布局
         */
        private LinearLayout mLoadLayout;

        public FooterViewHolder(View itemView) {
            super(itemView);
            mPbLoad = itemView.findViewById(R.id.pbLoad);
            mTvLoadText = itemView.findViewById(R.id.tvLoadText);
            mLoadLayout = itemView.findViewById(R.id.loadLayout);
        }
    }

    /**
     * 添加头数据
     *
     * @param items 数据
     */
    public void addHeaderItem(List<BeanRespArticle> items) {
        mDatas.addAll(0, items);
        notifyDataSetChanged();
    }

    /**
     * 添加尾数据
     *
     * @param items 数据
     */
    public void addFooterItem(List<BeanRespArticle> items) {
        mDatas.addAll(items);
        notifyDataSetChanged();
    }


    /**
     * 清空数据
     */
    public void clear() {
        mDatas.clear();
    }

    /**
     * 更新加载更多状态
     *
     * @param status 状态
     */
    public void changeMoreStatus(int status) {
        mLoadMoreStatus = status;
        notifyDataSetChanged();
    }

    /**
     * 获得指定的数据
     *
     * @param position 位置
     * @return 数据
     */
    public BeanRespArticle getItem(int position) {
        if (mDatas == null) {
            return null;
        }
        if (mDatas.size() <= 0) {
            return null;
        }
        if (mDatas.size() <= position) {
            return null;
        }
        return mDatas.get(position);
    }

    /**
     * 生成日期
     *
     * @param date 时间戳
     * @return 日期
     */
    private String creatTime(long date) {
        if (getDateTime(date, DEFAULT_DATE_FORMAT).equals(getDateTime(DEFAULT_DATE_FORMAT))) {
            //当天
            return getDateTime(date, "HH:mm");
        } else if (getDateTime(date, DEFAULT_DATE_FORMAT).equals(getDateTime(addDays(-1), DEFAULT_DATE_FORMAT))) {
            //昨天
            return ("昨天  " + getDateTime(date, "HH:mm"));
        } else if (getDateTime(date, "yyyy").equals(String.valueOf(getCurrentYear()))) {
            //当年
            return (getDateTime(date, "MM月dd日 HH:mm"));
        } else {
            //其他
            return (getDateTime(date, "yyyy年MM月dd日 HH:mm"));
        }
    }
}
