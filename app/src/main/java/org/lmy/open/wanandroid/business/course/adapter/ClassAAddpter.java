package org.lmy.open.wanandroid.business.course.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.lmy.open.wanandroid.core.base.BaseRecyclerAdapter;
import org.lmy.open.wanandroid.core.base.OnItemClickListener;

/**********************************************************************
 *
 * 一级分类适配器
 * @类名 ClassAAddpter
 * @包名 org.lmy.open.wanandroid.business.course.adapter
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public class ClassAAddpter extends BaseRecyclerAdapter {

    protected ClassAAddpter(OnItemClickListener listener) {
        super(listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onItemClick(View view) {

    }

    @Override
    public void onItemLongClick(View view) {

    }
}
