package com.lesehome.gsonexampleandroid;

import android.test.InstrumentationTestCase;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Json解析测试基类
 * <p/>
 * Created by hcp on 16/4/25.
 */
public abstract class BaseJsonDataTest extends InstrumentationTestCase {

    protected final String tag = this.getClass().getSimpleName();
    protected String jsonObject = null;
    protected Gson gson;

    public BaseJsonDataTest() {
        init();
        print("______________原始数据_____________________");
        print(jsonObject);
        print("______________End______________");
    }

    public abstract void init();

    public abstract void test() throws JsonSyntaxException;

    public void print(String message) {
        System.out.println("-->[" + tag + "]" + message);
    }
}
