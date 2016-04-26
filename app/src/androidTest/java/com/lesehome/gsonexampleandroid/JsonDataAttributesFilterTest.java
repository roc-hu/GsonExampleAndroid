package com.lesehome.gsonexampleandroid;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * 排除字段
 * <p>
 * Created by hcp on 16/4/7.
 */
public class JsonDataAttributesFilterTest extends BaseJsonDataTest {

    public class AttributesFilterBean {

        public transient String typeDate1;//方法1：排除transient字段
        protected String typeDate2; // 方法2：排除Modifier为指定类型的字段
        public String typeDate3;
        public String typeDate4;
    }

    public JsonDataAttributesFilterTest() {
        super();
    }

    @Override
    public void init() {
        jsonObject = "{\n" +
                "  \"typeDate1\": \"1461579555246\",\n" +
                "  \"typeDate2\": \"2016-03-19 09:59:38\",\n" +
                "  \"typeDate3\": \"2016-03-19\",\n" +
                "  \"typeDate4\": \"Sat Mar 19 09:59:38 GMT+08:00 2016\"\n" +
                "}";
        gson = new GsonBuilder()
                // 方法2：排除Modifier为指定类型的字段   方法2与方法1冲突，姑且只能选其一
//                .excludeFieldsWithModifiers(Modifier.PROTECTED)
                // 方法3：使用@Expose注解 注意，没有被 @Expose 标注的字段会被排除
//                .excludeFieldsWithoutExposeAnnotation()
                //方法4：使用ExclusionStrategy定制字段排除策略
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        if ("typeDate3".equals(f.getName())) {
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false; //过滤掉 类名包含 Bean的类
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