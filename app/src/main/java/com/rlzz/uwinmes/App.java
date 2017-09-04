package com.rlzz.uwinmes;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.rlzz.uwinmes.utils.BuglyUtil;
import com.rlzz.uwinmes.utils.LogUtil;
import com.squareup.leakcanary.LeakCanary;


/**
 * Created by monty on 2017/8/11.
 */

public class App extends Application {
    private static Application application;

    public static Application getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (this) {
            if (application == null) {
                application = this;
                AppCrashHandler.shareInstance(this);
                BuglyUtil.init(this);
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
    }


}
