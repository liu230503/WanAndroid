package org.lmy.open.wanandroid.business.collection.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public CollectAdapter(Context context, OnItemClickListener listener) {
        super(listener, new ArrayList());
        mContext = context;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.layout_collectlist_item, parent, false);
        onBindClickListener(itemView);
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
        itemViewHolder.mDate.setText("");
        itemViewHolder.mTitleView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

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

    /**
     * item
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {
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

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTitleView = itemView.findViewById(R.id.tv_title);
            mAuthor = itemView.findViewById(R.id.tv_author);
            mDate = itemView.findViewById(R.id.tv_date);
        }
    }
}
