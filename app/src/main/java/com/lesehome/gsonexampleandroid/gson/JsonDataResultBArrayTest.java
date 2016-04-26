package com.lesehome.gsonexampleandroid.gson;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lesehome.gsonexampleandroid.bean.ABean;
import com.lesehome.gsonexampleandroid.bean.BBean;
import com.lesehome.gsonexampleandroid.bean.ResponseBean;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hcp on 16/4/25.
 */
public class JsonDataResultBArrayTest extends BaseJsonDataTest {

    public JsonDataResultBArrayTest(Context context) {
        super(context,"json_data_result_b_array.json");
        gson = new Gson();
    }

    @Override
    public void test() throws JsonSyntaxException {
        Type type = new TypeToken<ResponseBean<BBean>>(){}.getType();
        ResponseBean<BBean> responseBean = gson.fromJson(jsonObject, type);
        if (responseBean != null) {
            print("responseBean isSuccess : "+responseBean.isSuccess());
            print("responseBean code : "+responseBean.code);
            print("responseBean code : "+responseBean.message);

            if(responseBean.result!=null){
                print("responseBean result appversion : " +responseBean.result.appversion);
                print("responseBean result appname : " +responseBean.result.appname);
                print("responseBean result list size : " +responseBean.result.list.size());
                for (int i = 0; i < responseBean.result.list.size(); i++) {
                    print("responseBean result title : [" +(i+1)+"]"+responseBean.result.list.get(i).title);
                    print("responseBean result url : [" +(i+1)+"]"+responseBean.result.list.get(i).url);
                }

            }else {
                print("responseBean result is null!");
            }
        } else {
            print("responseBean is null!");
        }
        print("==============================================");
    }
}
