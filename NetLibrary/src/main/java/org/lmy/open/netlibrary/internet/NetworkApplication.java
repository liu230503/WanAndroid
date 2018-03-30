package org.lmy.open.netlibrary.internet;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.lmy.open.utillibrary.PreferenceUtil;

import java.util.HashSet;

/**********************************************************************
 *
 *
 * @类名 NetworkApplication
 * @包名 org.lmy.open.netlibrary.internet
 * @author lmy
 * @创建日期 2018/3/26
 ***********************************************************************/
public class NetworkApplication extends Application {
    /**
     * TAG
     */
    private static final String TAG = NetworkApplication.class.getName();
    /**
     * cookie 登陆密码
     */
    public static final String KEY_USER_PASSWORD = "loginUserPassword";
    /**
     * cookie 登陆用户
     */
    public static final String KEY_USER_NAME = "loginUserName";
    /**
     * 持久化cookie
     */
    private HashSet<String> mCookies;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 单例对象
     */
    private static NetworkApplication sInstance;

    /**
     * Preference
     */
    private PreferenceUtil mPreferenceUtil;

    @Override
    public void onCreate() {
        Log.d(TAG, "================= onCreate UtilApplication =================");
        super.onCreate();
        sInstance = this;
        mCookies = new HashSet<>();
        mPreferenceUtil = PreferenceUtil.getInstance(mContext);
        mCookies.add(mPreferenceUtil.getString(KEY_USER_PASSWORD));
        mCookies.add(mPreferenceUtil.getString(KEY_USER_NAME));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = base;
    }

    public static NetworkApplication getInstance() {
        return sInstance;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public void onTerminate() {
        Log.d(TAG, "================= onTerminate UtilApplication =================");
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        Log.d(TAG, "================= onLowMemory UtilApplication =================");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        Log.d(TAG, "================= onTrimMemory UtilApplication =================");
        super.onTrimMemory(level);
    }

    public HashSet<String> getCookies() {
        return mCookies;
    }

    /**
     * 添加cookie
     *
     * @param cookie cookie
     */
    public void addCookie(String cookie) {
        if (cookie.indexOf(KEY_USER_PASSWORD) != -1) {
            mPreferenceUtil.putString(KEY_USER_PASSWORD, cookie);
            mCookies.add(cookie);
        }
        if (cookie.indexOf(KEY_USER_NAME) != -1) {
            mPreferenceUtil.putString(KEY_USER_NAME, cookie);
            mCookies.add(cookie);
        }
    }
}
