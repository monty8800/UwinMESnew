package com.rlzz.uwinmes.net;

/**
 * 后台返回json基础数据模型
 * Created by monty on 2017/7/18.
 */

public class ResponseModel<T> {
    public int code;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "ResponseModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
