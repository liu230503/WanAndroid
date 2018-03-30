package org.lmy.open.wanandroid.core.application;

import android.content.Context;
import android.content.res.Configuration;

import org.lmy.open.netlibrary.internet.NetworkApplication;

/**********************************************************************
 *
 *
 * @类名 NetworkLibraryApplication
 * @包名 org.lmy.open.wanandroid.core.application
 * @author lmy
 * @创建日期 2018/3/26
 ***********************************************************************/
public class NetworkLibraryApplication extends NetworkApplication implements IApplicationListener {
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
