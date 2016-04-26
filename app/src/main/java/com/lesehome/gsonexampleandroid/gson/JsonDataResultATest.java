package com.lesehome.gsonexampleandroid.gson;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lesehome.gsonexampleandroid.bean.ABean;
import com.lesehome.gsonexampleandroid.bean.ResponseBean;

import java.lang.reflect.Type;

/**
 * Created by hcp on 16/4/25.
 */
public class JsonDataResultATest extends BaseJsonDataTest {

    public JsonDataResultATest(Context context) {
        super(context,"json_data_result_a.json");
        gson = new Gson();
    }

    @Override
    public void test() throws JsonSyntaxException {
        Type type = new TypeToken<ResponseBean<ABean>>(){}.getType();
        ResponseBean<ABean> responseBean = gson.fromJson(jsonObject, type);
        if (responseBean != null) {
            print("responseBean isSuccess : "+responseBean.isSuccess());
            print("responseBean code : "+responseBean.code);
            print("responseBean code : "+responseBean.message);

            if(responseBean.result!=null){
                print("responseBean result title : " +responseBean.result.title);
                print("responseBean result url : " +responseBean.result.url);
            }else {
                print("responseBean result is null!");
            }
        } else {
            print("responseBean is null!");
        }
        print("==============================================");
    }
}
