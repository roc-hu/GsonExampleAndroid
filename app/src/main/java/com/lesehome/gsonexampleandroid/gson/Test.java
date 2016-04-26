package com.lesehome.gsonexampleandroid.gson;

import android.content.Context;

import com.google.gson.JsonSyntaxException;

/**
 * Created by hcp on 16/4/25.
 */
public class Test {

    public static void test(Context context) throws Exception {
//        testJsonDataBase(context);
//        testJsonDataA(context);
//        JsonDataResultArrayTest(context);
//        JsonDataResultBArrayTest(context);
//        JsonDataResultCArrayTest(context);
//        JsonDataResultShouldSkipTest(context);
        JsonDataResultTypeConversionTest(context);
    }

    private static void testJsonDataBase(Context context) throws JsonSyntaxException {
        new JsonDataBaseTest(context).test();
    }

    private static void testJsonDataA(Context context) throws JsonSyntaxException {
        new JsonDataResultATest(context).test();
    }

    private static void JsonDataResultArrayTest(Context context) throws JsonSyntaxException {
        new JsonDataResultArrayTest(context).test();
    }

    private static void JsonDataResultBArrayTest(Context context) throws JsonSyntaxException {
        new JsonDataResultBArrayTest(context).test();
    }

    private static void JsonDataResultCArrayTest(Context context) throws JsonSyntaxException {
        new JsonDataResultCArrayTest(context).test();
    }

    private static void JsonDataResultShouldSkipTest(Context context) throws JsonSyntaxException {
        new JsonDataResultShouldSkipTest(context).test();
    }

    private static void JsonDataResultTypeConversionTest(Context context) throws JsonSyntaxException {
        new JsonDataResultTypeConversionTest(context).test();
    }
}
