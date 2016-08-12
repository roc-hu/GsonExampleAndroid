# GsonExampleAndroid

###JSON介绍:[JSON介绍](http://www.json.org/json-zh.html)

Gson 示例  Android版本  version 0.01

####创建gson用到的GsonBuilder需要设置具体参数：

```
    Gson gson = new GsonBuilder()

        //不导出实体中没有用@Expose注解的属性
        .excludeFieldsWithoutExposeAnnotation()

        //支持Map的key为复杂对象的形式
        .enableComplexMapKeySerialization()

        //时间转化为特定格式
        .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")

        //会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效.
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)

        //对json结果格式化.
        .setPrettyPrinting()

        //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.
        //@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么
        //@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.
        .setVersion(1.0)

        //默认是GSON把HTML 转义的，但也可以设置不转义(禁止转义html标签)
        .disableHtmlEscaping()

        //把null值也转换，默认是不转换null值的，可以选择也转换,为空时输出为{a:null}，而不是{}
        .serializeNulls()

        // 禁此序列化内部类
        .disableInnerClassSerialization()

        //生成不可执行的Json（多了 )]}' 这4个字符）
        .generateNonExecutableJson()

        .create();

```


####Gson的注解：

```
1.使用@Expose可以区分实体中不想被序列化的属性

    @Expose标签的2个属性.

    1.1 deserialize (boolean) 反序列化 默认 true
    1.2 serialize  (boolean) 序列化 默认 true

    使用 new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();创建Gson对象，没有@Expose注释的属性将不会被序列化

2.使用@SerializedName标签定义属性序列化后的名字

    1.1 @SerializedName("name")
    1.2 @SerializedName(value = "emailAddress", alternate = {"email", "email_address"})
    当上面的三个属性(email_address、email、emailAddress)都中出现任意一个时均可以得到正确的结果。（针对2.4以上版本）

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


#补充博客：
[你真的会用Gson吗?Gson使用指南](http://www.jianshu.com/p/e740196225a4)
[Gson使用详解](http://blog.csdn.net/u012702547/article/details/46043549)
[Json框架选型:Android开发中应该使用哪一种主流json框架？](https://zhuanlan.zhihu.com/p/21919300)



