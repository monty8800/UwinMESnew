package com.rlzz.uwinmes.utils;

import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rlzz.uwinmes.App;

/**
 * Created by monty on 2017/8/16.
 */

public class ToastUtil {
    private static Toast mToast;

    /**
     * 传入文字
     */
    public static void show(String text) {

        if (mToast == null) {
            mToast = Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
            mToast.setText(text);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    /**
     * 传入资源文件
     */
    public static void show(int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getInstance(), resId, Toast.LENGTH_SHORT);
        } else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
            mToast.setText(resId);
        }
        mToast.show();
    }

    /**
     * 传入文字，带图片
     */
    public static void showImg(String text, int resImg) {

        if (mToast == null) {
            mToast = Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
            mToast.setText(text);
        }
        //添加图片的操作,这里没有设置图片和文字显示在一行的操作呢...
        LinearLayout view = (LinearLayout) mToast.getView();
        ImageView imageView = new ImageView(view.getContext());
        imageView.setImageResource(resImg);
        view.addView(imageView);

        mToast.show();
    }
}
