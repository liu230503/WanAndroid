package org.lmy.open.wanandroid.core.application;

import android.content.Context;
import android.content.res.Configuration;

import org.lmy.open.utillibrary.UtilApplication;

/**********************************************************************
 *
 *
 * @类名 UtilLibraryApplication
 * @包名 org.lmy.open.wanandroid.core.application
 * @author lmy
 * @创建日期 2018/3/5
 ***********************************************************************/
public class UtilLibraryApplication extends UtilApplication implements IApplicationListener {
    @Override
    public void onProxyCreate() {
        onCreate();
    }

    @Override
    public void onProxyAttachBaseContext(Context base) {
        attachBaseContext(base);
    }

    @Override
    public void onProxyConfigurationChanged(Configuration newConfig) {
        onConfigurationChanged(newConfig);
    }
}
