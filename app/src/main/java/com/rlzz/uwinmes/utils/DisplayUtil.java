package com.rlzz.uwinmes.utils;

import com.rlzz.uwinmes.App;

/**
 * Created by monty on 2017/9/1.
 */
public class DisplayUtil {

    public static int px2dp(float pxValue) {
        float density = App.getInstance().getResources().getDisplayMetrics().density;//得到设备的密度
        return (int) (pxValue / density + 0.5f);
    }

    public static int dp2px(float dpValue) {
        float density = App.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    public static int px2sp(float pxValue) {
        float scaleDensity = App.getInstance().getResources().getDisplayMetrics().scaledDensity;//缩放密度
        return (int) (pxValue / scaleDensity + 0.5f);
    }

    public static int sp2px(float spValue) {
        float scaleDensity = App.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scaleDensity + 0.5f);
    }


}
