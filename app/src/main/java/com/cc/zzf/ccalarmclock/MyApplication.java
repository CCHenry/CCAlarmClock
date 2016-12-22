package com.cc.zzf.ccalarmclock;

import android.app.Application;
import android.content.Context;


import org.xutils.x;



/**
 * Created by henryzheng on 2016/9/27.
 */
public class MyApplication extends Application {
    public static Context _context;


    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        _context=getApplicationContext();
    }
}
