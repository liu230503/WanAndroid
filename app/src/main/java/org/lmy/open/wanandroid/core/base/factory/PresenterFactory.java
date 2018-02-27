package org.lmy.open.wanandroid.core.base.factory;

import org.lmy.open.wanandroid.core.base.BasePresenter;
import org.lmy.open.wanandroid.core.base.BaseView;

/**********************************************************************
 *
 *
 * @类名 PresenterFactory
 * @包名 org.lmy.open.wanandroid.core.base.factory
 * @author lmy
 * @创建日期 2018/2/27
 ***********************************************************************/
public interface PresenterFactory<V extends BaseView, P extends BasePresenter<V>> {
    /**
     * 创建Presenter的接口方法
     *
     * @return 需要创建的Presenter
     */
    P createPresenter();
}
