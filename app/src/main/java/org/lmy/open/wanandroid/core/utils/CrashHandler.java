package org.lmy.open.wanandroid.core.utils;

import android.util.Log;

import com.umeng.analytics.MobclickAgent;

import org.lmy.open.utillibrary.ActivityManager;
import org.lmy.open.utillibrary.LogHelper;
import org.lmy.open.wanandroid.core.application.WanAndroidApp;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

/**********************************************************************
 *
 *
 * @类名 CrashHandler
 * @包名 org.lmy.open.wanandroid.core.utils
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public final class CrashHandler implements UncaughtExceptionHandler {

    /**
     * 单例对象.
     */
    private static CrashHandler sInstance;
    /**
     * 系统默认的UncaughtExceptionHandler.
     */
    private UncaughtExceptionHandler mHandler;

    /**
     * 私有构造函数.
     */
    private CrashHandler() {
    }

    /**
     * @return 单例对象
     */
    public static CrashHandler getInstance() {
        if (sInstance == null) {
            sInstance = new CrashHandler();
        }
        return sInstance;
    }

    /**
     * 初始化.
     */
    public void init() {
        mHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当异常发生时，捕获异常.
     *
     * @param thread 线程
     * @param ex     异常
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mHandler != null) {
            mHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ActivityManager.getInstance().popAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);

        }
    }

    /**
     * 自定义异常处理.
     *
     * @param ex 异常
     * @return boolean 是否处理
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return true;
        }
        String exception = this.saveCrashInfoToFile(ex);
        Log.e("exception", exception);
        return true;
    }

    /**
     * 保存异常信息到文件中.
     *
     * @param ex 异常
     * @return String 异常信息
     */
    public String saveCrashInfoToFile(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);

        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        String info = writer.toString();
        printWriter.close();
        MobclickAgent.reportError(WanAndroidApp.getBaseApplication().getApplicationContext(), info);
        LogHelper.d("收集错误信息： " + info);
        return info;
    }
}
