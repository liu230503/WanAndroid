package org.lmy.open.netlibrary.internet.api.retrofit;

import org.lmy.open.netlibrary.internet.api.ISendRequest;
import org.lmy.open.netlibrary.internet.base.BeanResponse;

/**********************************************************************
 *
 *
 * @类名 RetrofitLoader
 * @包名 org.lmy.open.netlibrary.internet.api.retrofit
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public final class RetrofitLoader extends ObjectLoader implements ISendRequest {
    /**
     * Api接口
     */
    private ApiService mApiService;

    public RetrofitLoader() {
        mApiService = RetrofitFactory.getInstance().getApi();
    }

    @Override
    public void getArticle(int pageNumber, final RequestListener listener) {
        mApiService.getArticle("\"article/list/\" + pageNumber + \"/json\"")
                .compose(this.<BeanResponse>setThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(BeanResponse response) throws Exception {
                        listener.onSuccess(response.getResp());
                    }

                    @Override
                    protected void onCodeError(BeanResponse response) throws Exception {
                        listener.onCodeError(response.getStatus(), response.getMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e, isNetWorkError);
                    }

                    @Override
                    protected void onRequestStart() {
                        listener.onRequestStart();
                    }

                    @Override
                    protected void onRequestEnd() {
                        listener.onRequestEnd();
                    }
                });
    }

}
