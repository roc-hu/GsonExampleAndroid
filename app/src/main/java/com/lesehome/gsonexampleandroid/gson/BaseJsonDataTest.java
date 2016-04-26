package com.lesehome.gsonexampleandroid.gson;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lesehome.gsonexampleandroid.util.AssetsUtil;

/**
 * Json解析测试基类
 * <p>
 * Created by hcp on 16/4/25.
 */
public abstract class BaseJsonDataTest {

    protected final String tag = this.getClass().getSimpleName();
    protected String jsonObject;
    protected Gson gson;

    public BaseJsonDataTest(Context context, String jsonName) {
        this.jsonObject = AssetsUtil.getString(context, jsonName);
        print("______________原始数据_____________________");
        print(jsonObject);
        print("______________End______________");
    }

    public abstract void test() throws JsonSyntaxException;

    public void print(String message) {
        System.out.println("-->[" + tag + "]" + message);
    }
}
