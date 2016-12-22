package com.cc.zzf.ccalarmclock.model;

import android.os.Environment;

import com.cc.zzf.ccalarmclock.MyApplication;


/**
 * Created by henryzheng on 2016/11/10.
 */
public class MyPath {
    /**
     * 缓存总目录
     *
     * @return
     */
    public static String getCacheDir() {
        if (isSDCARDMounted()) {
            return MyApplication._context.getExternalCacheDir().getPath();
        } else
            return null;
    }

    public static String getSmallPicSavePath() {
        return getCacheDir() + "/" + "smallPicSave";
    }

    private static boolean isSDCARDMounted() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * Created by zzf on 16/12/21.
     */

    /**
     * Created by zzf on 16/12/21.
     */

}
