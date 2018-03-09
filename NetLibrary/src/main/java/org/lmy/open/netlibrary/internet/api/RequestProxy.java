package org.lmy.open.netlibrary.internet.api;

import android.support.annotation.NonNull;

import org.lmy.open.netlibrary.internet.api.retrofit.RetrofitLoader;

/**********************************************************************
 *
 *
 * @类名 RequestProxy
 * @包名 org.lmy.open.netlibrary.internet.api
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public final class RequestProxy implements ISendRequest {
    /**
     * RetrofitLoader
     */
    private RetrofitLoader mRetrofitLoader;
    /**
     * 单例对象
     */
    private static RequestProxy sRequestProxy = null;

    private RequestProxy() {
        mRetrofitLoader = new RetrofitLoader();
    }

    /**
     * 单例方法
     *
     * @return 单例对象
     */
    public static RequestProxy getInstance() {
        if (sRequestProxy == null) {
            synchronized (RequestProxy.class) {
                if (sRequestProxy == null) {
                    sRequestProxy = new RequestProxy();
                }
            }
        }
        return sRequestProxy;
    }

    @Override
    public void getArticle(int pageNumber, @NonNull RequestListener listener) {
        mRetrofitLoader.getArticle(pageNumber, listener);
    }

    @Override
    public void getBanner(RequestListener listener) {
        mRetrofitLoader.getBanner(listener);
    }

    @Override
    public void getClass(RequestListener listener) {
        mRetrofitLoader.getClass(listener);
    }


}
