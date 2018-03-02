package org.lmy.open.wanandroid.core.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**********************************************************************
 *
 *
 * @类名 ProxyApplication
 * @包名 org.lmy.open.wanandroid.core.application
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
class ProxyApplication extends Application {
    /**
     * IApplicationListener
     */
    private IApplicationListener mListener;

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        this.mListener = initProxyApplication();
        if (this.mListener != null) {
            this.mListener.onProxyAttachBaseContext(base);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mListener != null) {
            mListener.onProxyCreate();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.mListener != null) {
            this.mListener.onProxyConfigurationChanged(newConfig);
        }
    }

    /**
     * 创建ImplementApplication
     *
     * @return ImplementApplication
     */
    private IApplicationListener initProxyApplication() {
        try {
            Class clazz = Class.forName("org.lmy.open.wanandroid.core.application.ImplementApplication");
            return (IApplicationListener) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {

            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

}
