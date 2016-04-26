package com.lesehome.gsonexampleandroid;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * 默认值的问题
 * <p>
 * Created by hcp on 16/4/7.
 */
public class JsonDataAttributesDefaultValueTest extends BaseJsonDataTest {

    public class AttributesFilterBean {

        public int value1;
        public String value2;
        public int value3;
        public int value4;
        public String value5;
    }

    public JsonDataAttributesDefaultValueTest() {
        super();
    }

    @Override
    public void init() {
        jsonObject = "{\n" +
                "    \"value1\": -1,\n" +
                "    \"value2\": null,\n" +
                "    \"value3\": 99.7,\n" +
                "    \"value5\": \"null\",\n" +
                "    \"value4\": \"7\"\n" +
                "  }";
        gson = new GsonBuilder()
                .registerTypeAdapter(String.class, STRING)   //所有String类型null替换为字符串“”
                .registerTypeAdapter(int.class, INTEGER) //int类型对float做兼容
                .create();
    }

    @Override
    public void test() throws JsonSyntaxException {
        AttributesFilterBean filterBean = gson.fromJson(jsonObject, AttributesFilterBean.class);
        if (filterBean != null) {
            print("AttributesFilterBean value1 : " + filterBean.value1);
            print("AttributesFilterBean value2 : " + filterBean.value2);
            print("AttributesFilterBean value3 : " + filterBean.value3);
            print("AttributesFilterBean value4 : " + filterBean.value4);
            print("AttributesFilterBean value5 : " + filterBean.value5);
        } else {
            print("AttributesFilterBean is null!");
        }
    }

    /**
     * 自定义adapter，解决由于数据类型为Int,实际传过来的值为Float，导致解析出错的问题
     * 目前的解决方案为将所有Int类型当成Double解析，再强制转换为Int
     */
    public static final TypeAdapter<Number> INTEGER = new TypeAdapter<Number>() {
        @Override
        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return 0;
            }
            try {
                double i = in.nextDouble();//当成double来读取
                return (int) i;//强制转为int
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    };
    /**
     * 自定义TypeAdapter ,null对象将被解析成空字符串
     */
    public static final TypeAdapter<String> STRING = new TypeAdapter<String>() {
        public String read(JsonReader reader) {
            try {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return "";//原先是返回Null，这里改为返回空字符串
                }
                return reader.nextString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        public void write(JsonWriter writer, String value) {
            try {
                if (value == null) {
                    writer.nullValue();
                    return;
                }
                writer.value(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}