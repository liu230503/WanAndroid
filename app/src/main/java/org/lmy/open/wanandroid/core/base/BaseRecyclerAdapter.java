package org.lmy.open.wanandroid.core.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**********************************************************************
 *
 *
 * @类名 BaseRecyclerAdapter
 * @包名 org.lmy.open.wanandroid.core.base
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemClickListener {
    /**
     * item点击事件监听
     */
    protected OnItemClickListener mOnItemClickListener;

    protected BaseRecyclerAdapter(OnItemClickListener listener) {
        if (listener == null) {
            mOnItemClickListener = this;
        }
        mOnItemClickListener = listener;
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

}
