package com.cc.zzf.ccalarmclock.adapt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.zzf.ccalarmclock.R;
import com.cc.zzf.ccalarmclock.i.MyItemClickListener;
import com.cc.zzf.ccalarmclock.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henryzheng on 2016/12/22.
 */
public  class MySongsRecycleAdapt extends RecyclerView.Adapter<MySongsRecycleAdapt.MyViewHolder> {
    private final Context _context;
    private final LayoutInflater _mLayoutInflater;
    private MyItemClickListener myItemClickListener;
    List<Song> songs=new ArrayList<>();
    public MySongsRecycleAdapt(Context context){
        _context = context;
        _mLayoutInflater = LayoutInflater.from(_context);
    }

    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View view = _mLayoutInflater.inflate(R.layout.activity_set_ring_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder( view,myItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(songs.get(position).getTitle());
    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MyItemClickListener _mItemClickListener;
        TextView tv;
        ImageView load;
        public MyViewHolder(final View view,MyItemClickListener listener) {
            super(view);
            view.setOnClickListener(this);
            tv= (TextView) view.findViewById(R.id.tv);
            this._mItemClickListener=listener;

        }




        @Override
        public void onClick(View view) {
            _mItemClickListener.onItemClick(view,getPosition());
        }
    }


    @Override
    public int getItemCount() {
        return songs.size();
    }
    public void addSong(Song song){
        songs.add(song);
        notifyDataSetChanged();
    }
}