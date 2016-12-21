package com.cc.zzf.ccalarmclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.cc.zzf.ccalarmclock.SetRingFromMediaActivity.SetRingActivtiy;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_alarm_clock_info)
public class AlarmClockInfoActivity extends BaseActivity {
//@ViewInject(R.id.rl0)
//private RelativeLayout rl0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Event(value = R.id.rl1,type= View.OnClickListener.class)
    private  void setRing(View view){
        startActivity(new Intent(AlarmClockInfoActivity.this, SetRingActivtiy.class));
    }
}
