package com.cc.zzf.ccalarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.cc.zzf.ccalarmclock.adapt.MyRecycleAdapt;
import com.cc.zzf.ccalarmclock.i.MyItemClickListener;
import com.cc.zzf.ccalarmclock.model.AlarmClockModel;
import com.cc.zzf.ccalarmclock.utils.CCDatabaseOpenHelper;
import com.cc.zzf.ccalarmclock.utils.CCLog;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;


@ContentView(R.layout.fragment_alarm_clock_list)
public class AlarmClockListFragment extends BaseFragment implements MyItemClickListener {
    @ViewInject(R.id.recycle_view)
    private RecyclerView recyclerView;
    @ViewInject(R.id.btn)
    ImageButton addBtn;

    LinearLayoutManager lin;
    MyRecycleAdapt myRecycleAdapt;
    DbManager db;
     public static final int oldClock=0;
    public static final int newClock=1;
    int position=0;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myRecycleAdapt=new MyRecycleAdapt(getActivity());
        lin = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lin);
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置增加或删除条目的动画
        recyclerView.setAdapter(myRecycleAdapt);
        myRecycleAdapt.setMyItemClickListener(this);
        setSqlDataToList();
    }
    private void setSqlDataToList() {
        db= CCDatabaseOpenHelper.getInstance();
        try {
           List<AlarmClockModel>  list=db.findAll(AlarmClockModel.class);
            if (list!=null)
            myRecycleAdapt.addAllClock(list);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    @Event(value = R.id.btn,type = View.OnClickListener.class)
    private void addClock(View view){
        Intent intent=new Intent(getActivity(),AlarmClockInfoActivity.class);
        intent.putExtra("requestType",newClock);
        startActivityForResult(intent,newClock);
//        myRecycleAdapt.addClock(new AlarmClockModel());
    }

    @Override
    public void onItemClick(View view, int postion) {
        CCLog.print("position"+postion);
        Intent intent=new Intent(getActivity(),AlarmClockInfoActivity.class);
        intent.putExtra("alarmClockModel",myRecycleAdapt.getClocks().get(postion));
        intent.putExtra("requestType",oldClock);
        this.position=postion;
        startActivityForResult(intent,oldClock);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==AlarmClockInfoActivity.hasContorl){
        if (requestCode==newClock){
            AlarmClockModel alarmClockModel= (AlarmClockModel) data.getSerializableExtra("alarmClockModel");
            myRecycleAdapt.addClock(alarmClockModel);
            alarmClockModel.setId(String.valueOf (myRecycleAdapt.getItemCount()));
            try {
                db.save(alarmClockModel);
            } catch (DbException e) {
                e.printStackTrace();e.printStackTrace();
            }
        }else if (requestCode==oldClock){
            AlarmClockModel alarmClockModel= (AlarmClockModel) data.getSerializableExtra("alarmClockModel");
            myRecycleAdapt.getClocks().set(position,alarmClockModel);// 替换闹钟
            myRecycleAdapt.notifyDataSetChanged();
            try {
               AlarmClockModel oldAlarmClockModel= db.findById(AlarmClockModel.class,alarmClockModel.getId());
                oldAlarmClockModel=alarmClockModel;
                db.update(oldAlarmClockModel);
            } catch (DbException e) {
                e.printStackTrace();e.printStackTrace();
            }
        }}
    }
}
