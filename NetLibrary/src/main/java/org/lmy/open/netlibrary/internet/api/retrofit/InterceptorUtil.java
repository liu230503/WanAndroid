package org.lmy.open.netlibrary.internet.api.retrofit;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**********************************************************************
 *拦截器工具类
 *
 * @类名 InterceptorUtil
 * @包名 org.lmy.open.netlibrary.internet.api.retrofit
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public class InterceptorUtil {
    /**
     * tag
     */
    private static final String TAG = InterceptorUtil.class.getName();

    /**
     * 日志拦截器
     *
     * @return 日志拦截器
     */
    public static HttpLoggingInterceptor logInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w(TAG, "log: " + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    /**
     * 处理和拦截http请求
     *
     * @return 拦截器
     */
    public static Interceptor headerInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request mRequest = chain.request();
                //token失效时,重新获取token
                //或者添加header等等
                return chain.proceed(mRequest);
            }
        };

    }
}
