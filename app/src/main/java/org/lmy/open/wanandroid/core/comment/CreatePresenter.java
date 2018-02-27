package org.lmy.open.wanandroid.core.comment;

import org.lmy.open.wanandroid.core.base.BasePresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**********************************************************************
 *
 *
 * @类名 CreatePresenter
 * @包名 org.lmy.open.wanandroid.core.comment
 * @author lmy
 * @创建日期 2018/2/27
 ***********************************************************************/
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends BasePresenter> value();
}
