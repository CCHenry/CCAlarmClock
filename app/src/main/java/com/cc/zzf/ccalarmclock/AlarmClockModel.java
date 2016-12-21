package com.cc.zzf.ccalarmclock;

/**
 * Created by zzf on 16/12/21.
 */
public class AlarmClockModel {
    private String time;
    private String songPath;
    private boolean isRepeat;
    private boolean isLanuch;

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
}
