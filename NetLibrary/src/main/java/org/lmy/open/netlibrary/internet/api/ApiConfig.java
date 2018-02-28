package org.lmy.open.netlibrary.internet.api;

/**********************************************************************
 *
 *
 * @类名 ApiConfig
 * @包名 org.lmy.open.netlibrary.internet.api
 * @author lmy
 * @创建日期 2018/2/28
 ***********************************************************************/
public final class ApiConfig {
    /**
     * Retrofit配置
     */
    public static class RetrofitConfig {
        /**
         * 超时时间 5s
         */
        public static final int DEFAULT_TIME_OUT = 5;
        /**
         * 读取超时时间10s
         */
        public static final int DEFAULT_READ_TIME_OUT = 10;

        /**
         * 写超时时间10s
         */
        public static final int DEFAULT_READ_WRITE_OUT = 10;

        /**
         * 请求地址
         */
        public static final String BASE_URL = "http://www.wanandroid.com/";
    }
}
