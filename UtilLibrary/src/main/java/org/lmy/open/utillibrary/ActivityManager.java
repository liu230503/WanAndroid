package org.lmy.open.utillibrary;

import android.app.Activity;
import android.text.TextUtils;

import java.util.Stack;

/**********************************************************************
 *
 *
 * @类名 ActivityManager
 * @包名 org.lmy.open.utillibrary
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public class ActivityManager {
    /**
     * activity栈
     */
    private static Stack<Activity> sActivityStack;
    /**
     * 单例对象
     */
    private static ActivityManager sInstance;
    /**
     * tag
     */
    private static final String TAG = "ActivityManager";
    /**
     * 类
     */
    private static Class sClass = null;

    /**
     * 单例模式
     *
     * @return ActivityManager
     */
    public static ActivityManager getInstance() {
        if (sInstance == null) {
            sInstance = new ActivityManager();
            sActivityStack = new Stack<>();
        }
        return sInstance;
    }

    /**
     * 获取当前正在运行的Activity
     *
     * @return Activity
     */
    public Activity getCurrentActivity() {
        if (sActivityStack != null && !sActivityStack.isEmpty()) {
            Activity activity = sActivityStack.lastElement();
            return activity;
        } else {
            return null;
        }
    }

    /**
     * 判断className是否当前的Activity
     *
     * @param className 类（路径�?
     * @return 是否
     */
    public boolean isCurrentActivity(final String className) {
        if ((!TextUtils.isEmpty(className)) && sActivityStack != null && !sActivityStack.isEmpty()) {
            Activity activity = sActivityStack.lastElement();
            if (activity != null && activity.getClass().getName().equals(className)) {
                return true;
            }
            activity = null;
        }
        return false;
    }

    /**
     * 压站
     *
     * @param activity Activity
     */
    public void pushActivity(final Activity activity) {
        if (sActivityStack == null) {
            sActivityStack = new Stack<>();
        }
        LogHelper.d("Push activity : " + activity.toString());
        sActivityStack.add(activity);
    }

    /**
     */
    public void popActivity() {
        if (sActivityStack != null && !sActivityStack.isEmpty()) {
            Activity activity = sActivityStack.lastElement();
            if (activity != null) {
                activity.finish();
                sActivityStack.remove(activity);
                LogHelper.d("Pop activity : " + activity.toString());
            }
            activity = null;
        }
    }

    /**
     * 将activity移除
     *
     * @param activity Activity
     */
    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            sActivityStack.remove(activity);
            LogHelper.d("销毁 activity : " + activity.toString());
        }
        activity = null;
    }

    /**
     * 弹出activity
     *
     * @param className 类名
     */
    public void popActivity(String className) {
        if ((!TextUtils.isEmpty(className)) && sActivityStack != null && !sActivityStack.isEmpty()) {
            for (Activity activity : sActivityStack) {
                if (activity != null && activity.getClass().getName().equals(className)) {
                    popActivity(activity);
                    break;
                }
            }
        }
        className = null;
    }

    /**
     * 结束Activity
     */
    public void popAllActivity() {
        while (true) {
            Activity activity = getCurrentActivity();
            if (activity == null) {
                break;
            }
            popActivity(activity);
        }
    }

    /**
     * 结束除了clazz外的Activity
     *
     * @param clazz Class
     */
    @SuppressWarnings("unchecked")
    public void popAllActivityExceptOne(final Class clazz) {
        while (true) {
            Activity activity = getCurrentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(clazz)) {
                break;
            }
            popActivity(activity);
        }
    }

    /**
     * 设置最后一个类
     * @param mclass 类
     */
    public void setLastActivity(Class mclass) {
        if (mclass != null) {
            sClass = mclass;
        }
    }
}

