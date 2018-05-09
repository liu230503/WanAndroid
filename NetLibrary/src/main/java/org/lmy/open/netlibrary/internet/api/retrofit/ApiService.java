package org.lmy.open.netlibrary.internet.api.retrofit;

import org.lmy.open.netlibrary.internet.api.BeanData;
import org.lmy.open.netlibrary.internet.base.BeanResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**********************************************************************
 *
 *
 * @类名 ApiService
 * @包名 org.lmy.open.netlibrary.internet.api.retrofit
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
interface ApiService {
    /**
     * get请求 获取首页文章
     *
     * @param url 路径
     * @return 结果
     */
    @GET
    Observable<BeanResponse> getArticle(@Url String url);

    /**
     * 获取首页轮播数据
     *
     * @return 结果
     */
    @GET("/banner/json")
    Observable<BeanResponse> getBanner();

    /**
     * 获取课程列表数据
     *
     * @return 结果
     */
    @GET("tree/json")
    Observable<BeanResponse> getClassTree();

    /**
     * 获取课程文章
     *
     * @return 结果
     */
    @GET
    Observable<BeanResponse> getClassArticle(@Url String url);

    /**
     * 登陆
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    @POST("/user/login")
    @FormUrlEncoded
    Observable<BeanResponse> onLogin(@Field("username") String userName, @Field("password") String password);

    /**
     * 注册
     *
     * @param userName   用户名
     * @param password   密码
     * @param repassword 重复密码
     * @return 结果
     */
    @POST("/user/register")
    @FormUrlEncoded
    Observable<BeanResponse> onRegister(@Field("username") String userName, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * get请求 获取收藏文章列表
     *
     * @param url 路径
     * @return 结果
     */
    @GET
    Observable<BeanResponse> getCollect(@Url String url);
    /**
     * post请求 收藏站内文章
     *
     * @param url 路径
     * @return 结果
     */
    @POST
    Observable<BeanResponse> onLike(@Url String url);


    /**
     * post请求 取消收藏站内文章
     *
     * @param url 路径
     * @return 结果
     */
    @POST
    Observable<BeanResponse> onUnLike(@Url String url);
}
