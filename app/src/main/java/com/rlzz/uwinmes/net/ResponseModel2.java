package com.rlzz.uwinmes.net;

/**
 * 后台返回json基础数据模型
 * Created by monty on 2017/7/18.
 */

public class ResponseModel2<T> {
    public boolean error;
    public T results;

    @Override
    public String toString() {
        return "ResponseModel2{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
