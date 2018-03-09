package org.lmy.open.wanandroid.business.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lmy.open.database.option.DtoOption;
import org.lmy.open.utillibrary.DensityUtils;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.core.base.BaseRecyclerAdapter;
import org.lmy.open.wanandroid.core.base.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 *
 *
 * @类名 OptionAdapter
 * @包名 org.lmy.open.wanandroid.business.main.adapter
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public class OptionAdapter extends BaseRecyclerAdapter {
    /**
     * 布局
     */
    private LayoutInflater mInflater;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 数据源
     */
    private List<DtoOption> mDatas;
    /**
     * 选中的item
     */
    private int mSelectedItem = 0;

    public OptionAdapter(Context context, OnItemClickListener listener) {
        super(listener);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.layout_optionlist_item, parent, false);
        onBindClickListener(itemView);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        if (position >= getItemCount()) {
            return;
        }
        DtoOption option = mDatas.get(position);
        if (position == mSelectedItem) {
            itemViewHolder.mNameView.setTextColor(mContext.getResources().getColor(R.color.theme_color));
            itemViewHolder.mNameView.setTextSize(DensityUtils.px2sp(mContext, 16));
        } else {
            itemViewHolder.mNameView.setTextColor(mContext.getResources().getColor(R.color.white));
            itemViewHolder.mNameView.setTextSize(DensityUtils.px2sp(mContext, 14));
        }
        itemViewHolder.mNameView.setText(option.getName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
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
     * 设置选中项
     *
     * @param position 选中项
     */
    public void setSelectedItem(int position) {
        mSelectedItem = position;
    }

    @Override
    public void onItemClick(View view) {

    }

    @Override
    public void onItemLongClick(View view) {

    }
}
