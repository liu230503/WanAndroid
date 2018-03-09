package org.lmy.open.wanandroid.core.base;

import android.view.View;

/**********************************************************************
 *
 *
 * @类名 OnItemClickListener
 * @包名 org.lmy.open.wanandroid.core.base
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public interface OnItemClickListener {
    /**
     * item点击事件回调
     *
     * @param view item
     */
    void onItemClick(View view);
    /**
     * item长按事件回调
     *
     * @param view item
     */
    void onItemLongClick(View view);
}
