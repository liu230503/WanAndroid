package org.lmy.open.utillibrary;

import android.app.Application;
import android.content.Context;

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
        LogHelper.d("启动 ：" + TAG);
        super.onCreate();
        sInstance = this;
        PathUtil.getInstance().init(mContext);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = base;
    }

    public static UtilApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onTerminate() {
        LogHelper.d("终止： " + TAG);
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        LogHelper.d(TAG + " 内存低");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        LogHelper.d(TAG + " 清理内存等级：" + level);
        super.onTrimMemory(level);
    }
}
