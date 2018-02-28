package org.lmy.open.netlibrary.internet.api.retrofit;

import org.lmy.open.netlibrary.internet.api.ApiConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**********************************************************************
 *
 *
 * @类名 RetrofitFactory
 * @包名 org.lmy.open.netlibrary.internet.api.retrofit
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
final class RetrofitFactory {
    /**
     * 单例对象
     */
    private static RetrofitFactory sRetrofitFactory;
    /**
     * 请求接口
     */
    private ApiService mApiService;

    private RetrofitFactory() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(ApiConfig.RetrofitConfig.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(ApiConfig.RetrofitConfig.DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(ApiConfig.RetrofitConfig.DEFAULT_READ_WRITE_OUT, TimeUnit.SECONDS)
                .addInterceptor(InterceptorUtil.headerInterceptor())
                .addInterceptor(InterceptorUtil.logInterceptor())
                .build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiConfig.RetrofitConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    /**
     * 单例方法
     *
     * @return 单例对象
     */
    public static RetrofitFactory getInstance() {
        if (sRetrofitFactory == null) {
            synchronized (RetrofitFactory.class) {
                if (sRetrofitFactory == null) {
                    sRetrofitFactory = new RetrofitFactory();
                }
            }
        }
        return sRetrofitFactory;
    }

    /**
     * 获取支持的接口
     *
     * @return 接口
     */
    public ApiService getApi() {
        return mApiService;
    }

}
