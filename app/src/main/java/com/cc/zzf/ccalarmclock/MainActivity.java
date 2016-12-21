package com.cc.zzf.ccalarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    void test() {
         AlarmManager alarmManager;
         PendingIntent pi;
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // 指定要启动的是Activity组件,通过PendingIntent调用getActivity来设置
        Intent intent = new Intent(MainActivity.this, ClockActivity.class);
        pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
//        btnSetClock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar currentTime = Calendar.getInstance();
//                // 弹出一个时间设置的对话框,供用户选择时间
//                new TimePickerDialog(MainActivity.this, 0,
//                        new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker view,
//                                                  int hourOfDay, int minute) {
//                                //设置当前时间
//                                Calendar c = Calendar.getInstance();
//                                c.setTimeInMillis(System.currentTimeMillis());
//                                // 根据用户选择的时间来设置Calendar对象
//                                c.set(Calendar.HOUR, hourOfDay);
//                                c.set(Calendar.MINUTE, minute);
//                                // ②设置AlarmManager在Calendar对应的时间启动Activity
//                                alarmManager.set(AlarmManager.RTC_WAKEUP,
//                                        c.getTimeInMillis(), pi);
//                                // 提示闹钟设置完毕:
//                                Toast.makeText(MainActivity.this, "闹钟设置完毕",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                        }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
//                        .get(Calendar.MINUTE), false).show();
//                btnbtnCloseClock.setVisibility(View.VISIBLE);
//            }
//        });
//
//        btnbtnCloseClock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alarmManager.cancel(pi);
//                btnbtnCloseClock.setVisibility(View.GONE);
//                Toast.makeText(MainActivity.this, "闹钟已取消", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        });
    }
}
