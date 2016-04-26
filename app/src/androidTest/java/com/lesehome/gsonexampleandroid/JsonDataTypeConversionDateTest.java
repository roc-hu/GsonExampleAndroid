package com.lesehome.gsonexampleandroid;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Data 类型转换问题
 * <p>
 * Created by hcp on 16/4/7.
 */
public class JsonDataTypeConversionDateTest extends BaseJsonDataTest {

    public class TypeDate {
        public Date typeDate1;
        public Date typeDate2;
        public Date typeDate3;
    }

    public JsonDataTypeConversionDateTest() {
        super();
    }

    @Override
    public void init() {
        jsonObject = "{\n" +
                "  \"typeDate1\": \"1461579555246\",\n" +
                "  \"typeDate2\": \"2016-03-19 09:59:38\",\n" +
                "  \"typeDate3\": \"Sat Mar 19 09:59:38 GMT+08:00 2016\"\n" +
                "}";
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, DATE)
                .create();
    }

    @Override
    public void test() throws JsonSyntaxException {
        TypeDate typeDate = gson.fromJson(jsonObject, TypeDate.class);
        if (typeDate != null) {
            print("typeDate typeDate1 : " + typeDate.typeDate1);
            print("typeDate typeDate2 : " + typeDate.typeDate2);
            print("typeDate typeDate3 : " + typeDate.typeDate3);
        } else {
            print("typeDate is null!");
        }
    }

    public static final TypeAdapter<Date> DATE = new TypeAdapter<Date>() {
        @Override
        public void write(JsonWriter out, Date value) throws IOException {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            out.value(format.format(value));
        }

        public Date read(JsonReader reader) {
            try {
                String millSec = reader.nextString();
                if (reader.getPath().endsWith("typeDate1")) {//1461579555246
                    java.util.Date date = new Date(Long.parseLong(millSec));
                    return date;
                } else if (reader.getPath().endsWith("typeDate2")) {//2016-03-19 09:59:38
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    return format.parse(millSec);
                } else if (reader.getPath().endsWith("typeDate3")) {//Sat Mar 19 09:59:38 GMT+08:00 2016
                    java.util.Date date = new Date(millSec);
                    return date;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    };
}