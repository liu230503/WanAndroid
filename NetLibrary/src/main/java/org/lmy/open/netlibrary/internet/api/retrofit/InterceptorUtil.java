package org.lmy.open.netlibrary.internet.api.retrofit;

import android.util.Log;

import org.lmy.open.netlibrary.BuildConfig;
import org.lmy.open.netlibrary.internet.NetworkApplication;
import org.lmy.open.utillibrary.LogHelper;
import org.lmy.open.utillibrary.NetWorkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
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
     * cookie 头
     */
    private static final String COOKIE = "Set-Cookie";

    /**
     * 日志拦截器
     *
     * @return 日志拦截器
     */
    public static HttpLoggingInterceptor logInterceptor() {
        if (!BuildConfig.DEBUG) {
            return null;
        }
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
                Request.Builder builder = chain.request().newBuilder();
                //token失效时,重新获取token
                //或者添加header等等
                for (String cookie : NetworkApplication.getInstance().getCookies()) {
                    builder.addHeader("Cookie", cookie);
                }
                return chain.proceed(builder.build());
            }
        };

    }

    /**
     * 缓存拦截
     *
     * @return 拦截器
     */
    public static Interceptor cacheControlInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkUtil.isNetworkConnected(NetworkApplication.getInstance().getContext())) {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }

                Response originalResponse = chain.proceed(request);
                if (NetWorkUtil.isNetworkConnected(NetworkApplication.getInstance().getContext())) {
                    // 有网络时 设置缓存为默认值
                    String cacheControl = request.cacheControl().toString();
                    return originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时 设置超时为1周
                    int maxStale = 60 * 60 * 24 * 7;
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
            }
        };
    }

    /**
     * 持久化cookie
     *
     * @return 拦截器
     */
    public static Interceptor cookiesInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                if (!response.headers(COOKIE).isEmpty()) {
                    for (String header : response.headers(COOKIE)) {
                        NetworkApplication.getInstance().addCookie(header);
                    }
                }
                return response;
            }
        };
    }
}
