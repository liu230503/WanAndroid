package org.lmy.open.database;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**********************************************************************
 *
 *
 * @类名 DataBaseApplication
 * @包名 org.lmy.open.database
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
public class DataBaseApplication extends Application {
    /**
     * tag
     */
    private static final String TAG = DataBaseApplication.class.getName();
    /**
     * 单例对象
     */
    private static Application mInstance;

    private Context mContext;

    @Override
    public void onCreate() {
        Log.d(TAG, "================= onCreate DataBaseApplication =================");
        super.onCreate();
        mInstance = this;
        DataBaseHelper.getInstance(mContext);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = base;
    }

    public static Application getInstance() {
        return mInstance;
    }

    @Override
    public void onTerminate() {
        Log.d(TAG, "================= onTerminate DataBaseApplication =================");
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        Log.d(TAG, "================= onLowMemory DataBaseApplication =================");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        Log.d(TAG, "================= onTrimMemory DataBaseApplication =================");
        super.onTrimMemory(level);
    }
}
