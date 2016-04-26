package com.lesehome.gsonexampleandroid.gson;

import android.content.Context;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lesehome.gsonexampleandroid.bean.BBean;
import com.lesehome.gsonexampleandroid.bean.ResponseBean;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 *
 *  修改字段json显示 把key=clist的改为key=list
 *
 * Created by hcp on 16/4/25.
 */
public class JsonDataResultCArrayTest extends BaseJsonDataTest {

    public JsonDataResultCArrayTest(Context context) {
        super(context,"json_data_result_c_array.json");
        gson = new GsonBuilder()
                .setFieldNamingStrategy(new FieldNamingStrategy() {
                    @Override
                    public String translateName(Field f) {
                        if ("list".equals(f.getName())) {
                            return "clist";
                        }
                        return f.getName();
                    }
                }).create();
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
                print("responseBean result price : " +responseBean.result.price);
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
