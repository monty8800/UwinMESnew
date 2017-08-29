package com.rlzz.uwinmes.net;

import android.util.ArrayMap;

import com.rlzz.uwinmes.utils.JsonUtil;
import com.rlzz.uwinmes.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by monty on 2017/8/14.
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
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
    private class HttpLogger implements HttpLoggingInterceptor.Logger {
        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(String message) {
            // 请求或者响应开始
            if (message.startsWith("--> POST")) {
                mMessage.setLength(0);
            }
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            if ((message.startsWith("{") && message.endsWith("}"))
                    || (message.startsWith("[") && message.endsWith("]"))) {
                message = JsonUtil.formatJson(JsonUtil.decodeUnicode(message));
            }
            mMessage.append(message.concat("\n"));
            // 响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                LogUtil.d(mMessage.toString());
            }
        }
    }


    /**
     * 获取Service对象，如果已经创建过直接取出，否则新建一个，并缓存起来
     *
     * @param service
     * @param <S>
     * @return
     */
    public synchronized <S> S getService(final Class<S> service) {
        String serviceName = service.getName();
        if (null != serviceByType.get(serviceName)) {
            return (S) serviceByType.get(serviceName);
        }
        S ser = retrofit.create(service);
        serviceByType.put(serviceName, ser);
        return ser;
    }
}
