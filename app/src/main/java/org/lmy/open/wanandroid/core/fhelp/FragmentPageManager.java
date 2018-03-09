package org.lmy.open.wanandroid.core.fhelp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.FragmentManager;

import org.lmy.open.utillibrary.LogHelper;
import org.lmy.open.wanandroid.core.enums.EnumFragmentIndex;

/**********************************************************************
 *
 *
 * @类名 FragmentPageManager
 * @包名 org.lmy.open.wanandroid.core.fhelp
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
public final class FragmentPageManager implements Handler.Callback {

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 页面跳转帮助类
     */
    private FragmentPageHelper mPageHelper;

    /**
     * 是否初始化完成
     */
    private boolean mIsInitialization = false;
    /**
     * 子线程
     */
    private HandlerThread mHandlerThread;


    /**
     * 单例对象
     */
    private static FragmentPageManager sFragmentPageManager = null;

    private FragmentPageManager() {
        mHandlerThread = new HandlerThread(FragmentPageManager.class.getName());
        mHandlerThread.start();

    }

    /**
     * 单例方法
     *
     * @return FragmentPageManager
     */
    public static FragmentPageManager getInstance() {
        if (sFragmentPageManager == null) {
            synchronized (FragmentPageManager.class) {
                if (sFragmentPageManager == null) {
                    sFragmentPageManager = new FragmentPageManager();
                }
            }
        }
        return sFragmentPageManager;
    }

    /**
     * 初始化
     *
     * @param context         上下文
     * @param containerViewId 容器布局id
     * @param fragmentManager 页面管理器
     */
    public void init(Context context, int containerViewId, FragmentManager fragmentManager) {
        mContext = context;
        mPageHelper = new FragmentPageHelper(context, fragmentManager);
        mPageHelper.setContainerViewId(containerViewId);
        mIsInitialization = true;
    }

    /**
     * 检测是否需要初始化
     */
    private void checkInit() {
        if (!mIsInitialization) {
            LogHelper.d("Must first be initialized to use");
            new Throwable("FragmentPageManager 使用前必须先进行初始化！");
        }
    }

    /**
     * 启动欢迎页面
     *
     * @param bundle          参数列表
     * @param switchAnimation 动画
     */
    public void onStartSplashFragment(Bundle bundle, ISwitchAnimation switchAnimation) {
        checkInit();
        mPageHelper.onStart(EnumFragmentIndex.SPLASH, bundle, switchAnimation);
    }

    /**
     * 启动主页面
     *
     * @param bundle          参数列表
     * @param switchAnimation 动画
     */
    public void onStartMainFragment(Bundle bundle, ISwitchAnimation switchAnimation) {
        checkInit();
        mPageHelper.onStart(EnumFragmentIndex.MAIN, bundle, switchAnimation);
    }

    /**
     * 启动登陆页面
     *
     * @param bundle          参数列表
     * @param switchAnimation 动画
     */
    public void onStartLoginFragment(Bundle bundle, ISwitchAnimation switchAnimation) {
        checkInit();
        mPageHelper.onStart(EnumFragmentIndex.LOGIN, bundle, switchAnimation);
    }

    /**
     * 启动我的收藏页面
     *
     * @param bundle          参数列表
     * @param switchAnimation 动画
     */
    public void onStartCollectionFragment(Bundle bundle, ISwitchAnimation switchAnimation) {
        checkInit();
        mPageHelper.onStart(EnumFragmentIndex.COLLECTION, bundle, switchAnimation);
    }

    /**
     * 启动私信页面
     *
     * @param bundle          参数列表
     * @param switchAnimation 动画
     */
    public void onStartLetterFragment(Bundle bundle, ISwitchAnimation switchAnimation) {
        checkInit();
        mPageHelper.onStart(EnumFragmentIndex.LETTER, bundle, switchAnimation);
    }

    /**
     * 启动常用网站页面
     *
     * @param bundle          参数列表
     * @param switchAnimation 动画
     */
    public void onStartCommonFragment(Bundle bundle, ISwitchAnimation switchAnimation) {
        checkInit();
        mPageHelper.onStart(EnumFragmentIndex.COMMON, bundle, switchAnimation);
    }

    /**
     * 启动设置页面
     *
     * @param bundle          参数列表
     * @param switchAnimation 动画
     */
    public void onStartSettingFragment(Bundle bundle, ISwitchAnimation switchAnimation) {
        checkInit();
        mPageHelper.onStart(EnumFragmentIndex.SETTING, bundle, switchAnimation);
    }

    /**
     * 启动关于页面
     *
     * @param bundle          参数列表
     * @param switchAnimation 动画
     */
    public void onStartAboutFragment(Bundle bundle, ISwitchAnimation switchAnimation) {
        checkInit();
        mPageHelper.onStart(EnumFragmentIndex.ABOUT, bundle, switchAnimation);
    }

    /**
     * 启动个人中心页面
     *
     * @param bundle          参数列表
     * @param switchAnimation 动画
     */
    public void onStartPersonalFragment(Bundle bundle, ISwitchAnimation switchAnimation) {
        checkInit();
        mPageHelper.onStart(EnumFragmentIndex.PERSONAL, bundle, switchAnimation);
    }

    /**
     * 启动课程树页面
     *
     * @param bundle          参数列表
     * @param switchAnimation 动画
     */
    public void onStartCourseFragment(Bundle bundle, ISwitchAnimation switchAnimation) {
        checkInit();
        mPageHelper.onStart(EnumFragmentIndex.COURSE, bundle, switchAnimation);
    }

    @Override
    public boolean handleMessage(Message message) {
        return false;
    }
}
