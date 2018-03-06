package org.lmy.open.netlibrary.internet.base;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 BeanResponse
 * @包名 org.lmy.open.netlibrary.internet.base
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public class BeanResponse{
    /**
     * 状态码
     */
    private int errorCode;
    /**
     * 异常信息
     */
    private String errorMsg;
    /**
     * 请求结果
     */
    private String data;

    public BeanResponse() {

    }

    public BeanResponse(int errorCode, String errorMsg, String data) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    /**
     * 请求是否成功
     *
     * @return 结果
     */
    public boolean isSuccess() {
        return errorCode >= 0;
    }
}
