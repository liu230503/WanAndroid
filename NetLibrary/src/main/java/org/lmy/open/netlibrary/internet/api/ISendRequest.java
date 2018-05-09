package org.lmy.open.netlibrary.internet.api;

/**********************************************************************
 *
 *
 * @类名 ISendRequest
 * @包名 org.lmy.open.netlibrary.internet.api
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public interface ISendRequest {
    /**
     * 获取首页文章
     *
     * @param pageNumber 页码
     * @param listener   监听器
     */
    void getArticle(int pageNumber, RequestListener listener);

    /**
     * 获取首页轮播数据
     *
     * @param listener 监听器
     */
    void getBanner(RequestListener listener);

    /**
     * 获取课程列表数据
     *
     * @param listener 监听器
     */
    void getClass(RequestListener listener);

    /**
     * 登陆
     *
     * @param userName 用户名
     * @param password 密码
     * @param listener 监听器
     */
    void onLogin(String userName, String password, RequestListener listener);

    /**
     * 注册
     *
     * @param userName 用户名
     * @param password 密码
     * @param listener 监听器
     */
    void onRegister(String userName, String password, RequestListener listener);

    /**
     * * 获取课程文章
     *
     * @param cid      分类id
     * @param page     页码
     * @param listener 监听器
     */
    void getClassArticle(int cid, int page, RequestListener listener);

    /**
     * 获取收藏列表
     *
     * @param pageNumber 页码
     * @param listener   监听器
     */
    void getCollect(int pageNumber, RequestListener listener);

    /**
     * 收藏点赞
     *
     * @param chapterId 文章id
     * @param listener  监听器
     */
    void onLike(int chapterId, RequestListener listener);

    /**
     * 取消收藏点赞
     *
     * @param chapterId 文章id
     * @param listener  监听器
     */
    void onUnLike(int chapterId, RequestListener listener);

    /**
     * 取消收藏点赞
     *
     * @param chapterId 文章id
     * @param originId  originId
     * @param listener  监听器
     */
    void onUnLike(int chapterId, int originId, RequestListener listener);

    /**
     * 请求结果监听
     */
    interface RequestListener {
        /**
         * 成功的回调
         *
         * @param data 数据
         */
        void onSuccess(String data);

        /**
         * 状态吗错误的回调
         *
         * @param errorCode    错误码
         * @param errorMessage 错误信息
         */
        void onCodeError(int errorCode, String errorMessage);

        /**
         * 请求失败的回调
         *
         * @param e              异常信息
         * @param isNetWorkError 是否由于网络原因导致
         */
        void onFailure(Throwable e, boolean isNetWorkError);

        /**
         * 开始请求
         */
        void onRequestStart();

        /**
         * 结束请求
         */
        void onRequestEnd();
    }

}
