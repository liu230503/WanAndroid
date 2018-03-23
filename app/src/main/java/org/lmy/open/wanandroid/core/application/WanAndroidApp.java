package org.lmy.open.wanandroid.core.application;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**********************************************************************
 *
 *
 * @类名 WanAndroidApp
 * @包名 org.lmy.open.wanandroid.core.application
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public final class WanAndroidApp extends BaseApplication {

    /**
     * 单例对象
     */
    private static WanAndroidApp sInstance = null;
    /**
     * 是否可以显示开始动画
     */
    private boolean mIsCanShowStartAnimation = false;

    @Override
    protected void init() {
        sInstance = this;
        setCanShowStartAnimation(true);
        initUmeng();
    }

    /**
     * 初始化common库
     * 参数1:上下文，不能为空
     * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
     * 参数3:Push推送业务的secret
     */
    private void initUmeng() {
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setEncryptEnabled(true);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

    }

    public Context getContext() {
        return mContext;
    }

    public static WanAndroidApp getInstance() {
        return sInstance;
    }

    public static void setInstance(WanAndroidApp instance) {
        sInstance = instance;
    }

    public boolean isCanShowStartAnimation() {
        return mIsCanShowStartAnimation;
    }

    public void setCanShowStartAnimation(boolean canShowStartAnimation) {
        mIsCanShowStartAnimation = canShowStartAnimation;
    }
}
