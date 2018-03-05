package org.lmy.open.wanandroid.core.application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import org.lmy.open.wanandroid.core.utils.CrashHandler;

/**********************************************************************
 *
 *
 * @类名 BaseApplication
 * @包名 org.lmy.open.wanandroid.core.application
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
abstract class BaseApplication extends ProxyApplication {
    /**
     * 设备id
     */
    private String mDeviceId;
    /**
     * 上下文
     */
    protected Context mContext;


    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext  = base;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(BaseApplication.class.getName(), "BaseApplication onCreate");
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init();
        mDeviceId = getDeviceId();
        init();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 获取设备id
     *
     * @return 设备id
     */
    @SuppressLint("MissingPermission")
    public String getDeviceId() {
        if (TextUtils.isEmpty(mDeviceId)) {
            try {
                TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
                mDeviceId = tm.getDeviceId();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mDeviceId;
    }

    /**
     * 获取版本号
     *
     * @return 版本号
     */
    public String getVersion() {
        String versionName = "";
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    protected abstract void init();

}
