package org.lmy.open.wanandroid.core.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 *
 *
 * @类名 ImplementApplication
 * @包名 org.lmy.open.wanandroid.core.application
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public class ImplementApplication extends Application implements IApplicationListener {
    /**
     * 类名全路径
     */
    private static List<String> sClassNames;
    /**
     * Application集合
     */
    private static List<IApplicationListener> sApplications;

    static {
        sClassNames = new ArrayList<>();
        sApplications = new ArrayList<>();
    }

    @Override
    public void onProxyCreate() {
        super.onCreate();
        for (IApplicationListener application : sApplications) {
            if (application != null) {
                application.onProxyCreate();
            }
        }
    }

    @Override
    public void onProxyAttachBaseContext(Context base) {
        super.attachBaseContext(base);
        initApplication();
        for (IApplicationListener application : sApplications) {
            if (application != null) {
                application.onProxyAttachBaseContext(base);
            }
        }
    }

    @Override
    public void onProxyConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        for (IApplicationListener application : sApplications) {
            if (application != null) {
                application.onProxyConfigurationChanged(newConfig);
            }
        }
    }

    /**
     * 创建Application
     */
    private void initApplication() {
        if (sApplications == null) {
            sApplications = new ArrayList<>();
        }
        for (String className : sClassNames) {
            try {
                Class clazz = Class.forName(className);
                sApplications.add((IApplicationListener) clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
