package org.lmy.open.netlibrary.internet.base;

/**********************************************************************
 *
 *
 * @类名 BeanReqPrgram
 * @包名 org.lmy.open.netlibrary.internet.base
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public class BeanReqPrgram<T> {
    /**
     * api
     */
    private String mApi;
    /**
     * 参数列表
     */
    private T mReqParams;

    public BeanReqPrgram() {

    }

    public BeanReqPrgram(String api, T reqParams) {
        super();
        this.mApi = api;
        this.mReqParams = reqParams;
    }

    public String getApi() {
        return mApi;
    }

    public void setApi(String api) {
        this.mApi = api;
    }

    public T getReqParams() {
        return mReqParams;
    }

    public void setReqParams(T reqParams) {
        this.mReqParams = reqParams;
    }
}
