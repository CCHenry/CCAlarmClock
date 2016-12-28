package com.cc.zzf.ccalarmclock.adapt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.zzf.ccalarmclock.R;
import com.cc.zzf.ccalarmclock.i.MyItemClickListener;
import com.cc.zzf.ccalarmclock.model.AlarmClockModel;
import com.cc.zzf.ccalarmclock.utils.CCDatabaseOpenHelper;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henryzheng on 2016/12/22.
 */
public  class MyRecycleAdapt extends RecyclerView.Adapter<MyRecycleAdapt.MyViewHolder> {
    private final Context _context;
    private final LayoutInflater _mLayoutInflater;
    private MyItemClickListener myItemClickListener;
    List<AlarmClockModel> clocks = new ArrayList<>();

    public MyRecycleAdapt(Context context) {
        _context = context;
        _mLayoutInflater = LayoutInflater.from(_context);
    }

    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = _mLayoutInflater.inflate(R.layout.fragment_alarm_clock_list_item, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view, myItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv1.setText(clocks.get(position).getTime());
        holder.checkBox.setChecked(clocks.get(position).isLanuch());
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            CompoundButton.OnCheckedChangeListener {
        MyItemClickListener _mItemClickListener;
        ImageView load;
        public TextView tv1;
        public CheckBox checkBox;

        public MyViewHolder(final View view, MyItemClickListener listener) {
            super(view);
            view.setOnClickListener(this);
            this._mItemClickListener = listener;
            tv1 = (TextView) view.findViewById(R.id.tv1);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox);
            checkBox.setOnCheckedChangeListener(this);
        }

        @Override
        public void onClick(View view) {
            _mItemClickListener.onItemClick(view, getPosition());
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            clocks.get(getPosition()).setLanuch(isChecked);
            DbManager db = CCDatabaseOpenHelper.getInstance();
            try {
                AlarmClockModel oldAlarmClockModel = db.findById(AlarmClockModel.class, clocks
                        .get(getPosition()).getId());
                oldAlarmClockModel.setLanuch(isChecked);
                db.update(oldAlarmClockModel);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public int getItemCount() {
        return clocks.size();
    }

    public void addClock(AlarmClockModel clockModel) {
        clocks.add(clockModel);
        notifyDataSetChanged();
    }

    public void addAllClock(List<AlarmClockModel> clockModels) {
        clocks.addAll(clockModels);
        notifyDataSetChanged();
    }

    public List<AlarmClockModel> getClocks() {
        return clocks;
    }
}
