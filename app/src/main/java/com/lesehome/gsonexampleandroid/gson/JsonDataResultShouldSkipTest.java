package com.lesehome.gsonexampleandroid.gson;

import android.content.Context;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lesehome.gsonexampleandroid.bean.ABean;
import com.lesehome.gsonexampleandroid.bean.BBean;
import com.lesehome.gsonexampleandroid.bean.ResponseBean;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * 过滤掉  不解析的字段
 * <p/>
 * Created by hcp on 16/4/25.
 */
public class JsonDataResultShouldSkipTest extends BaseJsonDataTest {

    public JsonDataResultShouldSkipTest(Context context) {
        super(context, "json_data_result_c_array.json");
        gson = new GsonBuilder()
                /**
                 * 过滤掉  不解析的字段
                 */
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        if (f.getName().contains("appversion")) {
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        //过滤掉 类名包含 Bean的类
//                            return clazz.getName().contains("Type");
                        if (clazz == Double.class) {
                            return true;
                        }
                        return false;
                    }
                }).create();
    }

    @Override
    public void test() throws JsonSyntaxException {
        Type type = new TypeToken<ResponseBean<BBean>>() {
        }.getType();
        ResponseBean<BBean> responseBean = gson.fromJson(jsonObject, type);
        if (responseBean != null) {
            print("responseBean isSuccess : " + responseBean.isSuccess());
            print("responseBean code : " + responseBean.code);
            print("responseBean code : " + responseBean.message);

            if (responseBean.result != null) {
                print("responseBean result appversion : " + responseBean.result.appversion);
                print("responseBean result appname : " + responseBean.result.appname);
                print("responseBean result price : " +responseBean.result.price);
            } else {
                print("responseBean result is null!");
            }
        } else {
            print("responseBean is null!");
        }
        print("==============================================");
    }
}
