package org.lmy.open.wanandroid.core.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.lmy.open.database.collect.DtoCollect;

import java.util.Collection;
import java.util.List;

/**********************************************************************
 *
 *
 * @类名 BaseRecyclerAdapter
 * @包名 org.lmy.open.wanandroid.core.base
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public abstract class BaseRecyclerAdapter<E> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemClickListener {
    /**
     * item点击事件监听
     */
    protected OnItemClickListener mOnItemClickListener;
    /**
     * 数据源
     */
    protected List<E> mDatas;

    protected BaseRecyclerAdapter(OnItemClickListener listener, List<E> datas) {
        if (listener == null) {
            mOnItemClickListener = this;
        } else {
            mOnItemClickListener = listener;
        }
        mDatas = datas;
    }

    /**
     * 为item绑定点击事件
     *
     * @param view item
     */
    protected void onBindClickListener(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mOnItemClickListener.onItemLongClick(view);
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 添加头数据
     *
     * @param items 数据
     */
    public void addHeaderItem(List<E> items) {
        if (items == null || items.size() <= 0) {
            return;
        }
        mDatas.addAll(0, items);
//        notifyItemRangeInserted(0, items.size() - 1);
        notifyDataSetChanged();
    }

    /**
     * 添加尾数据
     *
     * @param items 数据
     */
    public void addFooterItem(List<E> items) {
        if (items == null) {
            return;
        }
//        int positionStart = mDatas.size();
        mDatas.addAll(items);
        notifyDataSetChanged();
//        notifyItemRangeInserted(positionStart, mDatas.size() - 1);
    }

    /**
     * 清空数据
     */
    public void clear() {
        mDatas.clear();
    }
}
