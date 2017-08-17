package com.rlzz.uwinmes;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.rlzz.uwinmes.utils.LogUtil;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;


/**
 * Created by monty on 2017/8/11.
 */

public class App extends Application {
    private static Application application;
    private static final String BUGLY_APP_ID = "21cc9c4212";

    public static Application getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        /**
         * 腾讯Bugly，处理异常上报
         */
        Bugly.init(getApplicationContext(), BUGLY_APP_ID, BuildConfig.DEBUG);

        if (BuildConfig.DEBUG) {
            LogUtil.debugInit();
            //使用chrome调试网络和数据库  chrome://inspect/#devices
            Stetho.initializeWithDefaults(this);
            //内存泄漏检测
            LeakCanary.install(this);
        } else {
            LogUtil.releaseInit();
        }
    }
}
