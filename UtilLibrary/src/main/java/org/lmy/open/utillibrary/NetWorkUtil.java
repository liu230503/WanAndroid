package org.lmy.open.utillibrary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**********************************************************************
 *
 *
 * @类名 NetWorkUtil
 * @包名 org.lmy.open.utillibrary
 * @author lmy
 * @创建日期 2018/3/26
 ***********************************************************************/
public class NetWorkUtil {
    /**
     * 判断网络状态是否可用
     *
     * @param context 上下文
     * @return 结果
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = conManager.getActiveNetworkInfo();
        if (network != null) {
            return network.isAvailable();
        }
        return false;
    }
}
