package com.cc.zzf.ccalarmclock;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Display;


import com.cc.zzf.ccalarmclock.i.IHandlerListener;

import org.xutils.x;



/**
 * Created by henryzheng on 2016/9/27.
 */
public class BaseActivity extends FragmentActivity implements IHandlerListener {
    private Display mDisplay;
    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            OnHandlerListener(msg);
        }
    };
    public Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mDisplay = getWindowManager().getDefaultDisplay();
        context=this;
    }

    public int getWidth() {
        return mDisplay.getWidth();
    }

    public int getHight() {
        return mDisplay.getHeight();
    }

    @Override
    public void OnHandlerListener(Message object) {

    }
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler mHandler) {
        this.handler = mHandler;
    }

}
