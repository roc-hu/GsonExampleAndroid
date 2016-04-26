package com.lesehome.gsonexampleandroid.gson;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lesehome.gsonexampleandroid.bean.ResponseBean;

/**
 * Created by hcp on 16/4/25.
 */
public class JsonDataBaseTest extends BaseJsonDataTest {

    public JsonDataBaseTest(Context context) {
        super(context,"json_data_base.json");
        gson = new Gson();
    }

    @Override
    public void test() throws JsonSyntaxException {
        ResponseBean responseBean = gson.fromJson(jsonObject, ResponseBean.class);
        if (responseBean != null) {
            print("responseBean isSuccess : "+responseBean.isSuccess());
            print("responseBean code : "+responseBean.code);
            print("responseBean code : "+responseBean.message);
        } else {
            print("responseBean is null!");
        }
        print("==============================================");
    }
}
