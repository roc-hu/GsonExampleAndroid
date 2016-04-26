package com.lesehome.gsonexampleandroid;

import android.test.InstrumentationTestCase;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Field;

/**
 *
 * 修改json的key
 *
 * Created by hcp on 15/6/11.
 */
public class GsonTest_2 extends InstrumentationTestCase {
    public static final String tag = "GsonTest_2";

    public void test() throws Exception {
        String strJson = "{\"a\":true,\"b\":66,\"c\":\"数据c\",\"d\":8.88,\"e\":\"数据e\",\"type\":{\"id\":1,\"typeName\":\"类型\"},\"other\":{\"id\":2,\"otherName\":\"其他\"}}";

        log("****Original****************************");
        log(strJson + "\n" + GsonUtil.jsonFormatter(strJson));
        try {
            Car car = new Gson().fromJson(strJson, Car.class);
            /**
             * 修改字段json显示 把key=aa的改为key=a
             */
            Gson gson = new GsonBuilder()
                .setFieldNamingStrategy(new FieldNamingStrategy() {
                    @Override
                    public String translateName(Field f) {
                        if ("c".equals(f.getName())) {
                            log("把key:'c'修改为'ccc'");
                            return "ccc";
                        }
                        return f.getName();
                    }
                }).create();
            String result = gson.toJson(car);
            log("Compiled:");
            log(result + "\n" + GsonUtil.jsonFormatter(result));
            log("****End****************************");
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            log(e.getMessage());
        }
    }

    public void log(String message) {
        System.out.println("-->" + message);
    }

    public class Car {
        public boolean a;
        public int b;
        public String c;
        public double d;
        public String e;
        public Type type;
        public Other other;
    }

    public class Type {
        public int id;
        public String typeName;
    }

    public class Other {
        public int id;
        public String otherName;
    }
}