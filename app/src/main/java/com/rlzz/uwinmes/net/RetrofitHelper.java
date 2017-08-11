package com.rlzz.uwinmes.net;

import android.util.ArrayMap;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kelin on 2017/6/9.
 * RetrofitHelper
 */

public class RetrofitHelper {
    private volatile static RetrofitHelper instance;
    public static String baseUrl = "http://gank.io/api/";
    private Retrofit retrofit;
    private ArrayMap<String, Object> serviceByType = new ArrayMap<>();

    private RetrofitHelper() {
        initRetrofit();
    }

    private void initRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitHelper getInstance() {
        if (instance == null) {
            synchronized (RetrofitHelper.class) {
                if (instance == null) {
                    instance = new RetrofitHelper();
                }
            }
        }
        return instance;
    }


    /**
     * 获取Service对象，如果已经创建过直接取出，否则新建一个，并缓存起来
     *
     * @param service
     * @param <T>
     * @return
     */
    public synchronized <T> T getService(final Class<T> service) {
        String serviceName = service.getName();
        if (null != serviceByType.get(serviceName)) {
            return (T) serviceByType.get(serviceName);
        }
        T ser = retrofit.create(service);
        serviceByType.put(serviceName, ser);
        return ser;
    }
}
