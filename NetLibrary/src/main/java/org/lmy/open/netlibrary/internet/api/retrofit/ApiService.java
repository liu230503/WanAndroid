package org.lmy.open.netlibrary.internet.api.retrofit;

import org.lmy.open.netlibrary.internet.base.BeanResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
}
