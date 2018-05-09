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
    /**
     * 默认回调监听
     */
    private RequestListener mRequestListener = new RequestListener() {
        @Override
        public void onSuccess(String data) {

        }

        @Override
        public void onCodeError(int errorCode, String errorMessage) {

        }

        @Override
        public void onFailure(Throwable e, boolean isNetWorkError) {

        }

        @Override
        public void onRequestStart() {

        }

        @Override
        public void onRequestEnd() {

        }
    };

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
        mRetrofitLoader.getArticle(pageNumber, listener == null ? mRequestListener : listener);
    }

    @Override
    public void getBanner(RequestListener listener) {
        mRetrofitLoader.getBanner(listener == null ? mRequestListener : listener);
    }

    @Override
    public void getClass(RequestListener listener) {
        mRetrofitLoader.getClass(listener == null ? mRequestListener : listener);
    }

    @Override
    public void onLogin(String userName, String password, RequestListener listener) {
        mRetrofitLoader.onLogin(userName, password, listener == null ? mRequestListener : listener);
    }

    @Override
    public void onRegister(String userName, String password, RequestListener listener) {
        mRetrofitLoader.onRegister(userName, password, listener == null ? mRequestListener : listener);
    }

    @Override
    public void getClassArticle(int cid, int page, RequestListener listener) {
        mRetrofitLoader.getClassArticle(cid, page, listener == null ? mRequestListener : listener);
    }

    @Override
    public void getCollect(int pageNumber, RequestListener listener) {
        mRetrofitLoader.getCollect(pageNumber, listener == null ? mRequestListener : listener);
    }

    @Override
    public void onLike(int chapterId, RequestListener listener) {
        mRetrofitLoader.onLike(chapterId, listener == null ? mRequestListener : listener);
    }

    @Override
    public void onUnLike(int chapterId, RequestListener listener) {
        mRetrofitLoader.onUnLike(chapterId, listener == null ? mRequestListener : listener);
    }

    @Override
    public void onUnLike(int chapterId, int originId, RequestListener listener) {
        mRetrofitLoader.onUnLike(chapterId,originId, listener == null ? mRequestListener : listener);
    }


}
