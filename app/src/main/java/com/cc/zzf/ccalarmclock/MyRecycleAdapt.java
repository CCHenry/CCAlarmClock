package com.cc.zzf.ccalarmclock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.cc.zzf.ccalarmclock.i.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzf on 16/12/21.
 */
public class MyRecycleAdapt extends RecyclerView.Adapter<MyRecycleAdapt.MyViewHolder> {
    private final Context _context;
    private final LayoutInflater _mLayoutInflater;
    private MyItemClickListener myItemClickListener;
    List<AlarmClockModel> clocks=new ArrayList<>();
    public MyRecycleAdapt(Context context){
        _context = context;
        _mLayoutInflater = LayoutInflater.from(_context);
    }

    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View view = _mLayoutInflater.inflate(R.layout.fragment_alarm_clock_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder( view,myItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyItemClickListener _mItemClickListener;
        ImageView load;
        public MyViewHolder(final View view,MyItemClickListener listener) {
            super(view);
            view.setOnClickListener(this);
            this._mItemClickListener=listener;

        }




        @Override
        public void onClick(View view) {
            _mItemClickListener.onItemClick(view,getPosition());
        }
    }


    @Override
    public int getItemCount() {
        return clocks.size();
    }
    public void addClock(AlarmClockModel clockModel){
        clocks.add(clockModel);
        notifyDataSetChanged();
    }
}
