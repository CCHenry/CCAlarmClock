package com.cc.zzf.ccalarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cc.zzf.ccalarmclock.SetRingFromMediaActivity.SetRingActivitiy;
import com.cc.zzf.ccalarmclock.model.AlarmClockModel;
import com.cc.zzf.ccalarmclock.model.Song;
import com.cc.zzf.ccalarmclock.utils.CCLog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_alarm_clock_info)
public class AlarmClockInfoActivity extends BaseActivity {
    //@ViewInject(R.id.rl0)
//private RelativeLayout rl0;
    @ViewInject(R.id.tv1)
    private TextView tv1;
    @ViewInject(R.id.btn2)
    private Button btn2;
    @ViewInject(R.id.time_picker)
    private TimePicker timePicker;
    @ViewInject(R.id.checkbox0)
    CheckBox checkBox_repeat;
    @ViewInject(R.id.checkbox1)
    CheckBox checkBox_shake;
    @ViewInject(R.id.checkbox2)
    CheckBox checkBox_delete;
    @ViewInject(R.id.et)
    private EditText et;
    Song mySong;
    public static final int noControl = 0;
    public static final int hasContorl = 1;
    AlarmClockModel alarmClockModel;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySong = new Song();
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                time = hourOfDay + ":" + minute;
            }
        });
        if (getIntent().getIntExtra("requestType", 0) == AlarmClockListFragment.newClock)
            alarmClockModel = new AlarmClockModel();
        else {
            alarmClockModel = (AlarmClockModel) getIntent().getSerializableExtra("alarmClockModel");
            checkBox_repeat.setChecked(alarmClockModel.isRepeat());
            checkBox_shake.setChecked(alarmClockModel.isShake());
            checkBox_delete.setChecked(alarmClockModel.isLaunch_delete());
            et.setText(alarmClockModel.getDec());
            String[] time=alarmClockModel.getTime().split(":");
            timePicker.setCurrentHour(Integer.parseInt(time[0]));
            timePicker.setCurrentMinute(Integer.parseInt(time[1]));
            tv1.setText(alarmClockModel.getSongName());
        }
    }


    @Event(value = R.id.rl1, type = View.OnClickListener.class)
    private void setRing(View view) {
        startActivityForResult(new Intent(AlarmClockInfoActivity.this, SetRingActivitiy.class), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SetRingActivitiy.hasSelect) {
            Song song = (Song) data.getSerializableExtra("song");
            tv1.setText(song.getTitle());
            mySong = song;
//            alarmClockModel.set
        }
    }

    @Event(value = R.id.btn2, type = View.OnClickListener.class)
    private void saveRing(View view) {
        Intent intent = new Intent();
        alarmClockModel.setTime(timePicker.getCurrentHour().toString() + ":" + timePicker
                .getCurrentMinute().toString());
        CCLog.print(timePicker.getCurrentHour().toString() + ":" + timePicker.getCurrentMinute()
                .toString());
        alarmClockModel.setSongPath(mySong.getUrl());
        alarmClockModel.setDec(et.getText().toString());
        alarmClockModel.setLaunch_delete(checkBox_delete.isChecked());
        alarmClockModel.setRepeat(checkBox_repeat.isChecked());
        alarmClockModel.setShake(checkBox_shake.isChecked());
        alarmClockModel.setSongName(tv1.getText().toString());
        intent.putExtra("alarmClockModel", alarmClockModel);
        setResult(hasContorl, intent);
        finish();
    }

}
