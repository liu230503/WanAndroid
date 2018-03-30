package org.lmy.open.netlibrary.internet.api.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.lmy.open.netlibrary.internet.api.ApiConfig;
import org.lmy.open.netlibrary.internet.base.BeanResponse;
import org.lmy.open.utillibrary.path.PathUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
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
    /**
     * 缓存路径
     */
    private File mHttpCacheDirectory;
    /**
     * 缓存大小
     */
    private static final int sCacheSize = 10 * 1024 * 1024;

    private RetrofitFactory() {
        mHttpCacheDirectory = new File(PathUtil.getInstance().getNetworkRequestCachePath(), "retrofit");
        Cache cache = new Cache(mHttpCacheDirectory, sCacheSize);
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(ApiConfig.RetrofitConfig.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(ApiConfig.RetrofitConfig.DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(ApiConfig.RetrofitConfig.DEFAULT_READ_WRITE_OUT, TimeUnit.SECONDS)
                .addInterceptor(InterceptorUtil.headerInterceptor())
                .addInterceptor(InterceptorUtil.logInterceptor())
                .addInterceptor(InterceptorUtil.cacheControlInterceptor())
                .addInterceptor(InterceptorUtil.cookiesInterceptor())
                .cache(cache)
                .build();
        final Gson customGsonInstance = new GsonBuilder()
                .registerTypeAdapter(BeanResponse.class,
                        new GsonAnalysisFactory<BeanResponse>())
                .create();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiConfig.RetrofitConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
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
