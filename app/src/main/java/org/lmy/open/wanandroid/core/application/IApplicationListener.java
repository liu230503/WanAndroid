package org.lmy.open.wanandroid.core.application;

import android.content.Context;
import android.content.res.Configuration;

/**********************************************************************
 *
 *
 * @类名 IApplicationListener
 * @包名 org.lmy.open.wanandroid.core.application
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public interface IApplicationListener {
    /**
     * 创建
     */
    void onProxyCreate();

    /**
     * @param base
     */
    void onProxyAttachBaseContext(Context base);

    /**
     * @param newConfig
     */
    void onProxyConfigurationChanged(Configuration newConfig);
}
