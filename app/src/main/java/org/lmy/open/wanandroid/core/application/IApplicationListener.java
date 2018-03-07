package org.lmy.open.wanandroid.core.application;

import android.app.Application;
import android.content.ComponentCallbacks;
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
interface IApplicationListener {
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

    /**
     * 进程被杀死
     */
    void onLowMemory();

    /**
     * 低内存
     *
     * @param level 等级
     */
    void onTrimMemory(int level);

    /**
     * 注册ComponentCallbacks监听
     *
     * @param callback ComponentCallbacks
     */
    void registerComponentCallbacks(ComponentCallbacks callback);

    /**
     * 反注册ComponentCallbacks
     *
     * @param callback ComponentCallbacks
     */
    void unregisterComponentCallbacks(ComponentCallbacks callback);

    /**
     * 注册ActivityLifecycleCallbacks
     *
     * @param callback ActivityLifecycleCallbacks
     */
    void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback);

    /**
     * 反注册ActivityLifecycleCallbacks
     *
     * @param callback ActivityLifecycleCallbacks
     */
    void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback);

    /**
     * 注册OnProvideAssistDataListener
     *
     * @param callback OnProvideAssistDataListener
     */
    void registerOnProvideAssistDataListener(Application.OnProvideAssistDataListener callback);

    /**
     * 反注册OnProvideAssistDataListener
     *
     * @param callback OnProvideAssistDataListener
     */
    void unregisterOnProvideAssistDataListener(Application.OnProvideAssistDataListener callback);
}
