package com.cc.zzf.ccalarmclock.SetRingFromMediaActivity;

import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cc.zzf.ccalarmclock.BaseActivity;
import com.cc.zzf.ccalarmclock.R;
import com.cc.zzf.ccalarmclock.model.Song;
import com.cc.zzf.ccalarmclock.utils.CCLog;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_set_ring_activtiy)
public class SetRingActivtiy extends BaseActivity {
List<Song> songs=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        while (cursor.moveToNext()){
            Song song=new Song();
            song.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
        CCLog.print(song.getTitle())
;        }

    }
}
