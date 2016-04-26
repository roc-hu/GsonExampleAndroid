package com.lesehome.gsonexampleandroid;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

/**
 * Enum 类型转换问题
 * <p>
 * Created by hcp on 16/4/7.
 */
public class JsonDataTypeConversionEnumTest extends BaseJsonDataTest {

    public JsonDataTypeConversionEnumTest() {
        super();
    }

    @Override
    public void init() {
        jsonObject = "{\n" +
                "  \"state\": 1,\n" +
                "  \"title\": \"测试枚举\",\n" +
                "  \"color\": \"yellow\"\n" +
                "}";
        gson = new GsonBuilder()
                .registerTypeAdapter(State.class, new StateEnumSerializer())
                .registerTypeAdapter(Color.class, new ColorEnumSerializer())
                .create();
    }

    @Override
    public void test() throws JsonSyntaxException {
        TestBean testBean = gson.fromJson(jsonObject, TestBean.class);
        if (testBean != null) {
            print("testBean title : " + testBean.title);
            print("testBean state : " + testBean.state);
            print("testBean type : " + testBean.color.getName());
        } else {
            print("testBean is null!");
        }
    }

    public class ColorEnumSerializer implements JsonSerializer<Color>,
            JsonDeserializer<Color> {
        // json转为对象时调用,实现JsonDeserializer<Type>接口
        @Override
        public Color deserialize(JsonElement json, java.lang.reflect.Type typeOfT,
                                 JsonDeserializationContext context) throws JsonParseException {
            return Color.getColor(json.getAsString());
        }

        // 对象转为Json时调用,实现JsonSerializer<Type>接口
        @Override
        public JsonElement serialize(Color src, java.lang.reflect.Type typeOfSrc,
                                     JsonSerializationContext context) {
            return new JsonPrimitive(src.ordinal());
        }
    }


    public class StateEnumSerializer implements JsonSerializer<State>,
            JsonDeserializer<State> {
        // json转为对象时调用,实现JsonDeserializer<State>接口
        @Override
        public State deserialize(JsonElement json, java.lang.reflect.Type typeOfT,
                                 JsonDeserializationContext context) throws JsonParseException {
            if (json.getAsInt() < State.values().length)
                return State.values()[json.getAsInt()];
            return null;
        }

        // 对象转为Json时调用,实现JsonSerializer<State>接口
        @Override
        public JsonElement serialize(State src, java.lang.reflect.Type typeOfSrc,
                                     JsonSerializationContext context) {
            return new JsonPrimitive(src.ordinal());
        }
    }


    public class TestBean {
        public State state;
        public Color color;
        public String title;
    }

    public enum State {
        MEN, WOMEN
    }

    public enum Color {
        RED("红色", "red"), GREEN("绿色", "green"), BLANK("白色", "while"), YELLO("黄色", "yellow");
        // 成员变量
        private String name;
        private String en;

        // 构造方法
        private Color(String name, String en) {
            this.name = name;
            this.en = en;
        }

        // 普通方法
        public static String getName(String en) {
            for (Color c : Color.values()) {
                if (c.getEn().equals(en)) {
                    return c.name;
                }
            }
            return null;
        }

        // 普通方法
        public static Color getColor(String en) {
            for (Color c : Color.values()) {
                if (c.getEn().equals(en)) {
                    return c;
                }
            }
            return null;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }
    }
}