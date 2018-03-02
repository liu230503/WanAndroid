package org.lmy.open.database;

import android.app.Application;

/**********************************************************************
 *
 *
 * @类名 DataBaseApplication
 * @包名 org.lmy.open.database
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
public class DataBaseApplication extends Application {
    private static Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        DataBaseHelper.getInstance(mInstance);
    }

    public static Application getmInstance() {
        return mInstance;
    }
}
