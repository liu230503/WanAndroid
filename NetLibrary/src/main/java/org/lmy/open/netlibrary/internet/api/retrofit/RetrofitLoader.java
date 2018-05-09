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
        mApiService.getArticle("/article/list/" + pageNumber + "/json")
                .compose(this.<BeanResponse>setThread())
                .subscribe(new BaseObserver(listener) {
                    @Override
                    protected void onSuccess(BeanResponse response) throws Exception {
                        listener.onSuccess(response.getData().toString());
                    }

                    @Override
                    protected void onCodeError(BeanResponse response) throws Exception {
                        listener.onCodeError(response.getErrorCode(), response.getErrorMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e, isNetWorkError);
                    }
                });
    }

    @Override
    public void getBanner(final RequestListener listener) {
        mApiService.getBanner()
                .compose(this.<BeanResponse>setThread())
                .subscribe(new BaseObserver(listener) {
                    @Override
                    protected void onSuccess(BeanResponse response) throws Exception {
                        listener.onSuccess(response.getData().toString());
                    }

                    @Override
                    protected void onCodeError(BeanResponse response) throws Exception {
                        listener.onCodeError(response.getErrorCode(), response.getErrorMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e, isNetWorkError);
                    }
                });
    }

    @Override
    public void getClass(final RequestListener listener) {
        mApiService.getClassTree()
                .compose(this.<BeanResponse>setThread())
                .subscribe(new BaseObserver(listener) {
                    @Override
                    protected void onSuccess(BeanResponse response) throws Exception {
                        listener.onSuccess(response.getData().toString());
                    }

                    @Override
                    protected void onCodeError(BeanResponse response) throws Exception {
                        listener.onCodeError(response.getErrorCode(), response.getErrorMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e, isNetWorkError);
                    }

                });
    }

    @Override
    public void onLogin(String userName, String password, final RequestListener listener) {
        mApiService.onLogin(userName, password)
                .compose(this.<BeanResponse>setThread())
                .subscribe(new BaseObserver(listener) {
                    @Override
                    protected void onSuccess(BeanResponse response) throws Exception {
                        listener.onSuccess(response.getData().toString());
                    }

                    @Override
                    protected void onCodeError(BeanResponse response) throws Exception {
                        listener.onCodeError(response.getErrorCode(), response.getErrorMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e, isNetWorkError);
                    }
                });
    }

    @Override
    public void onRegister(String userName, String password, final RequestListener listener) {
        mApiService.onRegister(userName, password, password)
                .compose(this.<BeanResponse>setThread())
                .subscribe(new BaseObserver(listener) {
                    @Override
                    protected void onSuccess(BeanResponse response) throws Exception {
                        listener.onSuccess(response.getData().toString());
                    }

                    @Override
                    protected void onCodeError(BeanResponse response) throws Exception {
                        listener.onCodeError(response.getErrorCode(), response.getErrorMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e, isNetWorkError);
                    }
                });
    }

    @Override
    public void getClassArticle(int cid, int page, final RequestListener listener) {
        mApiService.getClassArticle("/article/list/" + page + "/json?cid=" + cid)
                .compose(this.<BeanResponse>setThread())
                .subscribe(new BaseObserver(listener) {
                    @Override
                    protected void onSuccess(BeanResponse response) throws Exception {
                        listener.onSuccess(response.getData().toString());
                    }

                    @Override
                    protected void onCodeError(BeanResponse response) throws Exception {
                        listener.onCodeError(response.getErrorCode(), response.getErrorMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e, isNetWorkError);
                    }

                });
    }

    @Override
    public void getCollect(int pageNumber, final RequestListener listener) {
        mApiService.getCollect("lg/collect/list/" + pageNumber + "/json")
                .compose(this.<BeanResponse>setThread())
                .subscribe(new BaseObserver(listener) {
                    @Override
                    protected void onSuccess(BeanResponse response) throws Exception {
                        listener.onSuccess(response.getData().toString());
                    }

                    @Override
                    protected void onCodeError(BeanResponse response) throws Exception {
                        listener.onCodeError(response.getErrorCode(), response.getErrorMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e, isNetWorkError);
                    }
                });
    }

    @Override
    public void onLike(int chapterId, final RequestListener listener) {
        mApiService.onLike("/lg/collect/" + chapterId + "/json")
                .compose(this.<BeanResponse>setThread())
                .subscribe(new BaseObserver(listener) {
                    @Override
                    protected void onSuccess(BeanResponse response) throws Exception {
                        listener.onSuccess(response.getData().toString());
                    }

                    @Override
                    protected void onCodeError(BeanResponse response) throws Exception {
                        listener.onCodeError(response.getErrorCode(), response.getErrorMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e, isNetWorkError);
                    }

                });
    }

    @Override
    public void onUnLike(int chapterId, final RequestListener listener) {
        mApiService.onUnLike("/lg/uncollect_originId/" + chapterId + "/json")
                .compose(this.<BeanResponse>setThread())
                .subscribe(new BaseObserver(listener) {
                    @Override
                    protected void onSuccess(BeanResponse response) throws Exception {
                        listener.onSuccess(response.getData().toString());
                    }

                    @Override
                    protected void onCodeError(BeanResponse response) throws Exception {
                        listener.onCodeError(response.getErrorCode(), response.getErrorMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        listener.onFailure(e, isNetWorkError);
                    }

                });
    }


}
