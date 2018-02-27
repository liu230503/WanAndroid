package org.lmy.open.wanandroid.core.base.factory;

import org.lmy.open.wanandroid.core.base.BasePresenter;
import org.lmy.open.wanandroid.core.base.BaseView;

/**********************************************************************
 *
 *
 * @类名 PresenterProxyInterface
 * @包名 org.lmy.open.wanandroid.core.base.factory
 * @author lmy
 * @创建日期 2018/2/27
 ***********************************************************************/
public interface PresenterProxyInterface<V extends BaseView, P extends BasePresenter<V>> {

    /**
     * 设置创建Presenter的工厂
     *
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(PresenterFactory<V, P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     *
     * @return 返回PresenterMvpFactory类型
     */
    PresenterFactory<V, P> getPresenterFactory();


    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     */
    P getPresenter();

}
