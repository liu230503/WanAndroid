package org.lmy.open.utillibrary;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**********************************************************************
 *
 *
 * @类名 ToastUtil
 * @包名 org.lmy.open.utillibrary
 * @author lmy
 * @创建日期 2018/3/27
 ***********************************************************************/
public class ToastUtil {
    /**
     * Toast
     */
    private static Toast sToast;

    /**
     * 显示长时间Toast
     *
     * @param context 上下文
     * @param msgId   内容id
     */
    public static void showToastLong(Context context, int msgId) {
        if (context == null) {
            return;
        }
        closeToast();
        sToast = Toast.makeText(context, msgId, Toast.LENGTH_LONG);
        sToast.show();
    }

    /**
     * 显示短时间Toast
     *
     * @param context 上下文
     * @param msgId   内容id
     */
    public static void showToastShort(Context context, int msgId) {
        if (context == null) {
            return;
        }
        closeToast();
        sToast = Toast.makeText(context, msgId, Toast.LENGTH_SHORT);
        sToast.show();
    }

    /**
     * 显示长时间Toast
     *
     * @param context 上下文
     * @param msg     内容
     */
    public static void showToastLong(Context context, String msg) {
        if (context == null || TextUtils.isEmpty(msg)) {
            return;
        }
        closeToast();
        sToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        sToast.show();
    }

    /**
     * 显示短时间Toast
     *
     * @param context 上下文
     * @param msg     内容
     */
    public static void showToastShort(Context context, String msg) {
        if (context == null || TextUtils.isEmpty(msg)) {
            return;
        }
        closeToast();
        sToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        sToast.show();
    }

    /**
     * 关闭toast
     */
    public static void closeToast() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }
}
