package com.cc.zzf.ccalarmclock.SetRingFromMediaActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.cc.zzf.ccalarmclock.BaseActivity;
import com.cc.zzf.ccalarmclock.R;
import com.cc.zzf.ccalarmclock.adapt.MySongsRecycleAdapt;
import com.cc.zzf.ccalarmclock.i.MyItemClickListener;
import com.cc.zzf.ccalarmclock.model.Song;
import com.cc.zzf.ccalarmclock.utils.CCLog;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_set_ring_activtiy)
public class SetRingActivitiy extends BaseActivity implements MyItemClickListener{
    @ViewInject(R.id.recycle_view)
    private RecyclerView recyclerView;
    List<Song> songs=new ArrayList<>();
    ImageButton addBtn;
    LinearLayoutManager lin;
    MySongsRecycleAdapt myRecycleAdapt;
    public static final int noSelect=0;
    public static final int hasSelect=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CCLog.print("SELECT SONGS");
        myRecycleAdapt=new MySongsRecycleAdapt(this);
        lin = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lin);
        recyclerView.setItemAnimator(new DefaultItemAnimator());// 设置增加或删除条目的动画
        recyclerView.setAdapter(myRecycleAdapt);
        myRecycleAdapt.setMyItemClickListener(this);

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        while (cursor.moveToNext()){
            Song song=new Song();
            song.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
            song.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
            CCLog.print(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
            myRecycleAdapt.addSong(song);songs.add(song );

;        }
CCLog.print("end");
    }

    @Override
    public void onItemClick(View view, int postion) {
        Intent intent=new Intent();
        intent.putExtra("song",songs.get(postion));
        setResult(hasSelect,intent);
        finish();
    }
}
