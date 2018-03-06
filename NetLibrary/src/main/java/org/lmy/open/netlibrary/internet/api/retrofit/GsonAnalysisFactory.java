package org.lmy.open.netlibrary.internet.api.retrofit;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.lmy.open.netlibrary.internet.api.JsonUtil;
import org.lmy.open.netlibrary.internet.base.BeanResponse;

import java.lang.reflect.Type;

/**********************************************************************
 *
 *
 * @类名 GsonAnalysisFactory
 * @包名 org.lmy.open.netlibrary.internet.api.retrofit
 * @author lmy
 * @创建日期 2018/3/6
 ***********************************************************************/
final class GsonAnalysisFactory<T> implements JsonDeserializer<BeanResponse> {

    @Override
    public BeanResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BeanResponse beanResponse = new BeanResponse();
        if (TextUtils.isEmpty(json.toString())) {
            return beanResponse;
        }
        beanResponse = JsonUtil.parseObject(json.toString(), BeanResponse.class);
        return beanResponse;
    }
}
