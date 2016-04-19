package com.lesehome.gsonexampleandroid.bean;

import java.io.Serializable;

/**
 * Response数据
 * <p/>
 * Created by hcp on 16/4/19.
 */
public class ResponseBean<T> implements Serializable {

    public int returncode = -1;
    public String message;
    public T result;

    /**
     * 判断请求 是否能拿到正确的结果
     *
     * @return
     */
    public boolean isSuccess() {
        if (returncode == 0) {
            return true;
        }
        return false;
    }
}
