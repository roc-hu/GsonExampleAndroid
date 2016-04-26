package com.lesehome.gsonexampleandroid.gson;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lesehome.gsonexampleandroid.bean.ResponseBean;
import com.lesehome.gsonexampleandroid.bean.TypeBean;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

/**
 * 类型转换
 * <p>
 * Created by hcp on 16/4/25.
 */
public class JsonDataResultTypeConversionTest extends BaseJsonDataTest {

    public JsonDataResultTypeConversionTest(Context context) {
        super(context, "json_data_result_type_conversion.json");
        gson = new GsonBuilder()
                //下列两段代码针对 属性未java.util.Date 类型，  值为 DateFormat.LONG 类型的数据
                .registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG)
                .registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG)
                .create();
    }

    public class DateDeserializer implements JsonDeserializer<Date> {

        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
        }
    }

    public class DateSerializer implements JsonSerializer<Date> {
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.getTime());
        }
    }

    @Override
    public void test() throws JsonSyntaxException {
        Type type = new TypeToken<ResponseBean<TypeBean>>() {
        }.getType();
        ResponseBean<TypeBean> responseBean = gson.fromJson(jsonObject, type);
        if (responseBean != null) {
            print("responseBean isSuccess : " + responseBean.isSuccess());
            print("responseBean code : " + responseBean.code);
            print("responseBean code : " + responseBean.message);

            if (responseBean.result != null) {
                print("responseBean result TypeBean typeString: " + responseBean.result.typeString);
                print("responseBean result TypeBean typeInt: " + responseBean.result.typeInt);
                print("responseBean result TypeBean typeDouble: " + responseBean.result.typeDouble);
                print("responseBean result TypeBean typeFloat: " + responseBean.result.typeFloat);
                print("responseBean result TypeBean typeDate1: " + responseBean.result.typeDate1);
                print("responseBean result TypeBean typeBoolean1: " + responseBean.result.typeBoolean1);
                print("responseBean result TypeBean typeBoolean2: " + responseBean.result.typeBoolean2);
                print("responseBean result TypeBean typeBoolean3: " + responseBean.result.typeBoolean3);
                print("responseBean result TypeBean typeBoolean4: " + responseBean.result.typeBoolean4);
                print("responseBean result TypeBean state: " + responseBean.result.state);
            } else {
                print("responseBean result is null!");
            }
        } else {
            print("responseBean is null!");
        }
        print("==============================================");
    }
}
