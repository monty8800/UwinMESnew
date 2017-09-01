package com.rlzz.uwinmes.utils;

import android.app.DatePickerDialog;
import android.content.Context;

import java.util.Calendar;


/**
 * Created by monty on 2017/8/30.
 */

public class DatePickerUtil {
    /**
     * 选择日期Dilog(选中当前日期)
     *
     * @param context
     * @param listener
     */
    public static void showDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener) {

        showDatePickerDialog(context, null, listener);
    }

    /**
     * 选择日期Dilog（选中传入的日期）
     *
     * @param context
     * @param listener
     */
    public static void showDatePickerDialog(Context context, String dateStr, DatePickerDialog.OnDateSetListener listener) {
        int[] date = formatDate2Int(dateStr);
        showDatePickerDialog(context, listener, date[0], date[1], date[2]);
    }

    /**
     * 选择日期Dilog（选中传入的日期）
     *
     * @param context
     * @param listener
     * @param year
     * @param month
     * @param dayOfMonth
     */
    public static void showDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener, int year, int month, int dayOfMonth) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, listener, year, month, dayOfMonth);
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
    }


    /**
     * 默认返回当前日期（如dateString解析有问题就直接返回当前日期，否则使用解析出来的日期）
     *
     * @param dateString
     * @return
     */
    public static int[] formatDate2Int(String dateString) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int dayOfMonty = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        int[] data = {year, month, dayOfMonty};
        if (!Check.isEmpty(dateString)) {
            String[] split = dateString.split("[.]");
            if (!Check.isEmpty(split) && split.length == 3) {
                for (int i = 0; i < split.length; i++) {
                    if (i == 1) { //代码处理月份减一
                        data[i] = Integer.parseInt(split[i]) - 1;
                    } else {
                        data[i] = Integer.parseInt(split[i]);
                    }
                }
            }
        }
        return data;
    }

    public static String formatDate2String(int year, int month, int dayOfMonth) {
        month += 1; //显示给用户时月份+1
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(year);
        stringBuffer.append(".");
        addPrefix(month, stringBuffer);
        stringBuffer.append(".");
        addPrefix(dayOfMonth, stringBuffer);
        return stringBuffer.toString();
    }

    private static void addPrefix(int num, StringBuffer stringBuffer) {
        if (num <= 9) {
            stringBuffer.append("0");
        }
        stringBuffer.append(num);

    }

}
