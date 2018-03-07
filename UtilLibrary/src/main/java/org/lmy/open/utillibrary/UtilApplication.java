package org.lmy.open.utillibrary;

import android.app.Application;
import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import org.lmy.open.utillibrary.imageload.LoadImageProxy;
import org.lmy.open.utillibrary.path.PathUtil;

/**********************************************************************
 *
 *
 * @类名 UtilApplication
 * @包名 org.lmy.open.utillibrary
 * @author lmy
 * @创建日期 2018/3/5
 ***********************************************************************/
public class UtilApplication extends Application {
    private static final String TAG = UtilApplication.class.getName();
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 单例对象
     */
    private static UtilApplication sInstance;

    @Override
    public void onCreate() {
        Log.d(TAG,"================= onCreate UtilApplication =================");
        super.onCreate();
        sInstance = this;
        PathUtil.getInstance().init(mContext);
        LoadImageProxy.getInstance().init(mContext);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = base;
    }

    public static UtilApplication getInstance() {
        return sInstance;
    }

    public Context getContext(){
        return mContext;
    }

    @Override
    public void onTerminate() {
        Log.d(TAG,"================= onTerminate UtilApplication =================");
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        Log.d(TAG,"================= onLowMemory UtilApplication =================");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        Log.d(TAG,"================= onTrimMemory UtilApplication =================");
        super.onTrimMemory(level);
    }
}
