package org.lmy.open.wanandroid.business.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.bean.BeanRespClassify;
import org.lmy.open.wanandroid.business.main.bean.BeanRespClassifyChildren;
import org.lmy.open.wanandroid.core.base.BaseRecyclerAdapter;
import org.lmy.open.wanandroid.core.base.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**********************************************************************
 *
 * 一级分类适配器
 * @类名 ClassAAdapter
 * @包名 org.lmy.open.wanandroid.business.course.adapter
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public class ClassAAdapter extends BaseRecyclerAdapter {
    /**
     * 布局
     */
    private LayoutInflater mInflater;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 选中的item
     */
    private int mSelectedItem = 0;

    public ClassAAdapter(Context context, OnItemClickListener listener) {
        super(listener, new ArrayList<>());
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.layout_classa_item, parent, false);
        onBindClickListener(itemView);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        if (position >= getItemCount()) {
            return;
        }
        BeanRespClassify bean = (BeanRespClassify) mDatas.get(position);
        if (bean != null) {
            bean.setOrder(position);
            if (position == mSelectedItem) {
                itemViewHolder.mNameView.setTextColor(mContext.getResources().getColor(R.color.theme_color));
            } else {
                itemViewHolder.mNameView.setTextColor(mContext.getResources().getColor(R.color.gray_9e9e9e));
            }
            itemViewHolder.mNameView.setText(bean.getName());
            itemViewHolder.mNameView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onItemClick(View view) {
    }

    @Override
    public void onItemLongClick(View view) {

    }

    /**
     * item布局
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        /**
         * 名称
         */
        private TextView mNameView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mNameView = itemView.findViewById(R.id.tv_name);
        }
    }

    /**
     * 清空数据
     */
    @Override
    public void clear() {
        mDatas.clear();
    }

    /**
     * 设置选中项
     *
     * @param position 选中项
     */
    public void setSelectedItem(int position) {
        mSelectedItem = position;
        notifyDataSetChanged();
    }

    /**
     * 获取选中项
     *
     * @return 选中项
     */
    public int getSelectedItem() {
        return mSelectedItem;
    }

    public List<BeanRespClassifyChildren> getItem(int position) {
        return ((BeanRespClassify) mDatas.get(position)).getChildren();
    }
}
