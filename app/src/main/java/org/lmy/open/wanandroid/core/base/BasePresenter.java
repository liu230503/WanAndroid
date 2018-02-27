package org.lmy.open.wanandroid.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**********************************************************************
 *
 *
 * @类名 BasePresenter
 * @包名 org.lmy.open.wanandroid.core.base
 * @author lmy
 * @创建日期 2018/2/27
 ***********************************************************************/
public abstract class BasePresenter<V extends BaseView> {
    /**
     * View接口
     */
    private V mView;

    /**
     * Presenter被创建后调用
     *
     * @param savedState 被意外销毁后重建后的Bundle
     */
    public void onCreatePersenter(@Nullable Bundle savedState) {
    }

    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPersenter() {
    }

    /**
     * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
     * 时机相同
     *
     * @param outState
     */
    public void onSaveInstanceState(Bundle outState) {
    }

    /**
     * 绑定V层
     *
     * @param view v层接口
     */
    public void bindView(V view) {
        this.mView = view;
    }

    /**
     * 解绑绑定V层
     */
    public void unBindView() {
        this.mView = null;
    }

    /**
     * 获取V层
     *
     * @return v层接口
     */
    public V getView() {
        return mView;
    }

}
