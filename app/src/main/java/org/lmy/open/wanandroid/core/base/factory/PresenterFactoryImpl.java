package org.lmy.open.wanandroid.core.base.factory;

import org.lmy.open.wanandroid.core.base.BasePresenter;
import org.lmy.open.wanandroid.core.base.BaseView;
import org.lmy.open.wanandroid.core.comment.CreatePresenter;

/**********************************************************************
 *
 *
 * @类名 PresenterFactoryImpl
 * @包名 org.lmy.open.wanandroid.core.base.factory
 * @author lmy
 * @创建日期 2018/2/27
 ***********************************************************************/
public class PresenterFactoryImpl<V extends BaseView, P extends BasePresenter<V>> implements PresenterFactory<V, P> {
    /**
     * 需要创建的Presenter的类型
     */
    private final Class<P> mPresenterClass;


    private PresenterFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }

    /**
     * 根据注解创建Presenter的工厂实现类
     *
     * @param viewClazz 需要创建Presenter的V层实现类
     * @param <V>       当前View实现的接口类型
     * @param <P>       当前要创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends BaseView, P extends BasePresenter<V>> PresenterFactoryImpl<V, P> createFactory(Class<?> viewClazz) {
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if (annotation != null) {
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterFactoryImpl<V, P>(aClass);
    }

    @Override
    public P createPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }
}
