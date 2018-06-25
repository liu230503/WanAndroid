package org.lmy.open.netlibrary.internet.api.retrofit;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.lmy.open.netlibrary.internet.api.ISendRequest;
import org.lmy.open.netlibrary.internet.base.BeanResponse;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**********************************************************************
 *
 *
 * @类名 BaseObserver
 * @包名 org.lmy.open.netlibrary.internet.api.retrofit
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public abstract class BaseObserver implements Observer<BeanResponse> {
    /**
     * Tag
     */
    private static final String TAG = BaseObserver.class.getName();
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 请求监听器
     */
    private ISendRequest.RequestListener mRequestListener;

    public BaseObserver(Context cxt) {
        this.mContext = cxt;
    }

    public BaseObserver(ISendRequest.RequestListener listener) {
        mRequestListener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        try {
            onRequestStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNext(BeanResponse beanResponse) {
        try {
            onRequestEnd();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (beanResponse.isSuccess()) {
            if (beanResponse != null) {
                try {
                    onSuccess(beanResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    onFailure(new Throwable("网络异常"), false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                onCodeError(beanResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.w(TAG, "Retrofit is Error !!! Error Code: ");
        try {
            onRequestEnd();
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 结束请求
     * @throws Exception 异常
     */
    protected void onRequestEnd() throws Exception{
        if (mRequestListener != null) {
            mRequestListener.onRequestEnd();
        }
    }

    /**
     * 返回成功
     *
     * @param response 结果
     * @throws Exception 异常
     */
    public void onSuccess(BeanResponse response) throws Exception{
        if (mRequestListener != null) {
            mRequestListener.onSuccess(response.getData());
        }
    }

    /**
     * 返回失败
     *
     * @param e              错误内容
     * @param isNetWorkError 是否是网络错误
     * @throws Exception 异常
     */
    public void onFailure(Throwable e, boolean isNetWorkError) throws Exception{
        if (mRequestListener != null) {
            mRequestListener.onFailure(e, isNetWorkError);
        }
    }

    /**
     * 返回成功了,但是code错误
     *
     * @param response 结果
     * @throws Exception 异常
     */
    public void onCodeError(BeanResponse response) throws Exception {
        if (mRequestListener != null) {
            mRequestListener.onCodeError(response.getErrorCode(), response.getErrorMsg());
        }
    }

    /**
     * 开始请求
     *  @throws Exception 异常
     */
    protected void onRequestStart() throws Exception {
        if (mRequestListener != null) {
            mRequestListener.onRequestStart();
        }
    }
}
