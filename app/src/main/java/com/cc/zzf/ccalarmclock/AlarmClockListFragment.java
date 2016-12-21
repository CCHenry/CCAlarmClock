package com.cc.zzf.ccalarmclock;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.cc.zzf.ccalarmclock.i.MyItemClickListener;
import com.cc.zzf.ccalarmclock.utils.CCLog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;


@ContentView(R.layout.fragment_alarm_clock_list)
public class AlarmClockListFragment extends BaseFragment implements MyItemClickListener {
    @ViewInject(R.id.recycle_view)
    private RecyclerView recyclerView;
    @ViewInject(R.id.btn)
    ImageButton addBtn;
    LinearLayoutManager lin;
    MyRecycleAdapt myRecycleAdapt;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myRecycleAdapt=new MyRecycleAdapt(getActivity());
        lin = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lin);
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置增加或删除条目的动画
        recyclerView.setAdapter(myRecycleAdapt);
        myRecycleAdapt.setMyItemClickListener(this);
    }
    @Event(value = R.id.btn,type = View.OnClickListener.class)
    private void addClock(View view){

        myRecycleAdapt.addClock(new AlarmClockModel());
    }

    @Override
    public void onItemClick(View view, int postion) {
        CCLog.print("position"+postion);
        startActivity(new Intent(getActivity(),AlarmClockInfoActivity.class));
    }
}
