package org.lmy.open.netlibrary.internet.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 JsonUtil
 * @包名 org.lmy.open.netlibrary.internet.api
 * @author lmy
 * @创建日期 2018/3/6
 ***********************************************************************/
public final class JsonUtil {
    /**
     * @param text  json
     * @param clazz 类型
     * @param <T>   类
     * @return 结果
     */
    public static <T> T parseObject(String text, Class<T> clazz) {

        return JSON.parseObject(text, clazz);
    }

    /**
     * @param text  json
     * @param clazz 类型
     * @param <T>   类
     * @return 结果
     */
    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }
}
