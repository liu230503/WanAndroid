package org.lmy.open.wanandroid.business.collection.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.lmy.open.database.collect.DtoCollect;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.core.base.BaseRecyclerAdapter;
import org.lmy.open.wanandroid.core.base.OnItemClickListener;

import java.util.ArrayList;

/**********************************************************************
 *
 *
 * @类名 CollectAdapter
 * @包名 org.lmy.open.wanandroid.business.collection.adapter
 * @author lmy
 * @创建日期 2018/3/28
 ***********************************************************************/
public class CollectAdapter extends BaseRecyclerAdapter {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 布局
     */
    private LayoutInflater mInflater;
    /**
     * 侧滑菜单按钮监听
     */
    private OnSideslipButtonListener mSideslipButtonListener;

    public CollectAdapter(Context context, OnItemClickListener listener) {
        super(listener, new ArrayList());
        mContext = context;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.layout_collectlist_sideslipitem, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        if (position >= mDatas.size()) {
            return;
        }
        DtoCollect dtoCollect = (DtoCollect) mDatas.get(position);
        itemViewHolder.mTitleView.setText(dtoCollect.getTitle());
        itemViewHolder.mAuthor.setText(dtoCollect.getAuthor());
        itemViewHolder.mDate.setText(dtoCollect.getChapterName());
        itemViewHolder.mTitleView.setTag(position);
        onBindClickListener(itemViewHolder.mItemLayout);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 获取item
     *
     * @param position 位置
     * @return 数据
     */
    public DtoCollect getItem(int position) {
        if (position >= mDatas.size()) {
            return null;
        }
        return (DtoCollect) mDatas.get(position);
    }

    @Override
    public void onItemClick(View view) {

    }

    @Override
    public void onItemLongClick(View view) {

    }

    public void setSideslipButtonListener(OnSideslipButtonListener sideslipButtonListener) {
        mSideslipButtonListener = sideslipButtonListener;
    }

    /**
     * item
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        /**
         * item布局
         */
        private RelativeLayout mItemLayout;
        /**
         * 标题
         */
        private TextView mTitleView;
        /**
         * 作者
         */
        private TextView mAuthor;
        /**
         * 日期
         */
        private TextView mDate;
        /**
         * 编辑按钮
         */
        private RelativeLayout mLoyaltyBtn;
        /**
         * 删除按钮
         */
        private RelativeLayout mDeleteBtn;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemLayout = itemView.findViewById(R.id.rll_item);
            mTitleView = itemView.findViewById(R.id.tv_title);
            mAuthor = itemView.findViewById(R.id.tv_author);
            mDate = itemView.findViewById(R.id.tv_date);
            mLoyaltyBtn = itemView.findViewById(R.id.btn_loyalty);
            mDeleteBtn = itemView.findViewById(R.id.btn_delete);
            mLoyaltyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mSideslipButtonListener != null) {
                        mSideslipButtonListener.onLoyaltyClick((int) mTitleView.getTag());
                    }
                }
            });
            mDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (int) mTitleView.getTag();
                    if (mSideslipButtonListener != null) {
                        mSideslipButtonListener.onDeleteClick(position);
                    }
                }
            });
        }
    }

    /**
     * 侧滑按钮监听
     */
    public interface OnSideslipButtonListener {
        /**
         * 编辑按钮点击回调
         *
         * @param position 位置
         */
        void onLoyaltyClick(int position);

        /**
         * 删除按钮点击回调
         *
         * @param position 位置
         */
        void onDeleteClick(int position);
    }
}
