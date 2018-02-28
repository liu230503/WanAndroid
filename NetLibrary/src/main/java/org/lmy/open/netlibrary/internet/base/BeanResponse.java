package org.lmy.open.netlibrary.internet.base;

/**********************************************************************
 *
 *
 * @类名 BeanResponse
 * @包名 org.lmy.open.netlibrary.internet.base
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public class BeanResponse {
    /**
     * 状态码
     */
    private int mStatus;
    /**
     * 异常信息
     */
    private String mMsg;
    /**
     * 请求结果
     */
    private String mResp;

    public BeanResponse() {

    }

    public BeanResponse(int status, String msg, String resp) {
        super();
        this.mStatus = status;
        this.mMsg = msg;
        this.mResp = resp;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        this.mStatus = status;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        this.mMsg = msg;
    }

    public String getResp() {
        return mResp;
    }

    public void setResp(String resp) {
        this.mResp = resp;
    }

    /**
     * 请求是否成功
     *
     * @return 结果
     */
    public boolean isSuccess() {
        return mStatus >= 0;
    }
}
