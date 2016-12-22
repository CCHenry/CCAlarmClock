package com.cc.zzf.ccalarmclock.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by zzf on 16/12/21.
 */
@Table(name = "alarmClockModel")
public class AlarmClockModel implements Serializable{
    @Column(name="id",isId = true)
    private String id;
    @Column(name="time")
    private String time;
    @Column(name="songPath")
    private String songPath;
    @Column(name="songName")
    private String songName;
    @Column(name="isRepeat")
    private boolean isRepeat=false;
    @Column(name="isShake")
    private boolean isShake=false;
    @Column(name="isLanuch")
    private boolean isLanuch=false;
    @Column(name="launch_delete")
    private boolean launch_delete=false;
    @Column(name="dec")
    private String dec="";
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean repeat) {
        isRepeat = repeat;
    }

    public boolean isLanuch() {
        return isLanuch;
    }

    public void setLanuch(boolean lanuch) {
        isLanuch = lanuch;
    }

    public boolean isShake() {
        return isShake;
    }

    public void setShake(boolean shake) {
        isShake = shake;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public boolean isLaunch_delete() {
        return launch_delete;
    }

    public void setLaunch_delete(boolean launch_delete) {
        this.launch_delete = launch_delete;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
