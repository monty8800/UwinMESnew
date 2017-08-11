package com.rlzz.uwinmes.net;


import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by monty on 2017/7/18.
 */

public abstract class RequestCallBack<T extends ResponseModel> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        Log.d("monty", "[RequestCallBack] request -> " + call.request().method() + " - " + call.request().url());
        if (response.raw().code() == 200) {
            if (response.body().code == 200) {
                Log.d("monty", "[RequestCallBack] onResponse -> " + response.body().toString());

                onSuccess(response, (T) response.body().data);
                finish();
            } else if (response.body().code == 0) { // 无效的Token，token已过期，需要重新跳转到登录页面重新登录
                Log.d("monty", "[RequestCallBack] onAutoLogin -> " + response.body().toString());
                onAutoLogin();
            } else {
                onFailure(response.body().msg);
            }
        } else {
            try {
                onFailure(response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure(t.getMessage());

        Log.e("monty", "[RequestCallBack] request -> " + call.request().method() + " - " + call.request().url());
        Log.e("monty", "[RequestCallBack] onFailure -> " + t.getMessage());
        finish();
    }

    public abstract void onSuccess(Response<T> response, T n);

    public abstract void onFailure(String message);

    public void finish() {
    }

    public void onAutoLogin() {
//        LoginUtil.startLoginActivity();
    }
}
