package com.rlzz.uwinmes.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by monty on 2017/8/11.
 */

public class FileUtil {

    private FileUtil() {
        throw new UnsupportedOperationException("can't instantiate ...");
    }

    public static File getExternalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }

    public static String getExternalCacheDirAbsolutePath(Context context) {
        return getExternalCacheDir(context).getAbsolutePath();
    }

    public static String assetFile2String(String fileName, Context context) {
        String Result = "";
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            bufReader = new BufferedReader(inputReader);
            String line;

            while ((line = bufReader.readLine()) != null) Result += line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeIO(inputReader, bufReader);

        return Result;
    }


    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null) return;
        try {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    closeable.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}