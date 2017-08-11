package com.rlzz.uwinmes.net;

/**
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
