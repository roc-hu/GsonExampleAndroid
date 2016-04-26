package com.lesehome.gsonexampleandroid;


import com.google.gson.FieldNamingStrategy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Field;

/**
 * 修改json的key
 * <p>
 * Created by hcp on 16/4/7.
 */
public class JsonDataAttributesTranslateNameTest extends BaseJsonDataTest {

    public class AttributesFilterBean {

        public String typeDate1;
        public String typeDate2;
        public String typeDate3;
        public String typeDate4;
    }

    public JsonDataAttributesTranslateNameTest() {
        super();
    }

    @Override
    public void init() {
        jsonObject = "{\n" +
                "  \"typeDate1\": \"1461579555246\",\n" +
                "  \"type2\": \"2016-03-19 09:59:38\",\n" +
                "  \"typeDate3\": \"2016-03-19\",\n" +
                "  \"typeDate4\": \"Sat Mar 19 09:59:38 GMT+08:00 2016\"\n" +
                "}";
        gson = new GsonBuilder()
                .setFieldNamingStrategy(new FieldNamingStrategy() {
                    @Override
                    public String translateName(Field f) {
                        if ("typeDate2".equals(f.getName())) {
                            return "type2";
                        }
                        return f.getName();
                    }
                })
                .create();
    }

    @Override
    public void test() throws JsonSyntaxException {
        AttributesFilterBean filterBean = gson.fromJson(jsonObject, AttributesFilterBean.class);
        if (filterBean != null) {
            print("typeDate typeDate1 : " + filterBean.typeDate1);
            print("typeDate typeDate2 : " + filterBean.typeDate2);
            print("typeDate typeDate3 : " + filterBean.typeDate3);
            print("typeDate typeDate4 : " + filterBean.typeDate4);
        } else {
            print("typeDate is null!");
        }
    }
}