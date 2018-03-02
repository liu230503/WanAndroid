package org.lmy.open.wanandroid.core.application;

import android.content.Context;
import android.content.res.Configuration;

import org.lmy.open.database.DataBaseApplication;

/**********************************************************************
 *
 *
 * @类名 DbApplication
 * @包名 org.lmy.open.wanandroid.core.application
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
public class DbApplication extends DataBaseApplication implements IApplicationListener {
    @Override
    public void onProxyCreate() {
        onCreate();
    }

    @Override
    public void onProxyAttachBaseContext(Context base) {
        onProxyAttachBaseContext(base);
    }

    @Override
    public void onProxyConfigurationChanged(Configuration newConfig) {
        onProxyConfigurationChanged(newConfig);
    }
}