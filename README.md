# GsonExampleAndroid
Gson 示例  Android版本  version 0.01

补充博客：[你真的会用Gson吗?Gson使用指南](http://www.jianshu.com/p/e740196225a4)


####创建gson用到的GsonBuilder需要设置具体参数：

```
    Gson gson = new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性
        .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
        .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//时间转化为特定格式
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)//会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效.
        .setPrettyPrinting() //对json结果格式化.
        .setVersion(1.0)
        .disableHtmlEscaping()//默认是GSON把HTML 转义的，但也可以设置不转义
        .serializeNulls()//把null值也转换，默认是不转换null值的，可以选择也转换,为空时输出为{a:null}，而不是{}
        .create();

```

####泛型的使用：

```

Type type = new TypeToken<ResponseBean<ABean>>(){}.getType();
ResponseBean<ABean> responseBean = new Gson.fromJson(jsonObject, type);

{
  "code": 0,
  "message": "请求成功",
  "result": {
    "title": "测试标题",
    "url": "http://www.lesehome.com"
  }
}

/**
 * Response数据
 */
public class ResponseBean<T> implements Serializable {

    public int code = -1;
    public String message;
    public T result;

    /**
     * 判断请求 是否能拿到正确的结果
     *
     * @return
     */
    public boolean isSuccess() {
        if (code == 0) {
            return true;
        }
        return false;
    }

}

public class ABean implements Serializable {
    public String title;
    public String url;
}

```

####特殊示例：

* 修改默认值的问题         JsonDataAttributesDefaultValueTest.class
* 修改json的key          JsonDataAttributesTranslateNameTest.class
* 排除不需要字段           JsonDataAttributesFilterTest.class
* Data 类型转换问题       JsonDataTypeConversionDateTest.class
* Enum 类型转换问题       JsonDataTypeConversionEnumTest.class


####常用解析示例com.lesehome.gsonexampleandroid.gson包下：


```
JsonDataBaseTest.class
JsonDataResultArrayTest.class
JsonDataResultATest.class
JsonDataResultBArrayTest.class
JsonDataResultCArrayTest.class
JsonDataResultShouldSkipTest.class
JsonDataResultTypeConversionTest.class
```




