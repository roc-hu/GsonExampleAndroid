package com.lesehome.gsonexampleandroid.util;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 读取assets文件
 * Created by hcp on 16/4/19.
 */
public class AssetsUtil {
    /**
     * 读取assets文件
     * @param context
     * @param fileName
     * @return
     */
    public static String getString(Context context, String fileName) {
        String json = null;
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int length;
            while ((length = in.read(buffer)) != -1)// 读取数据
                out.write(buffer, 0, length); // 写到内存
            in.close();
            out.close();
            byte[] data = out.toByteArray(); // 获取内存中的所有数据
            json = new String(data);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
