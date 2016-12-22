package com.cc.zzf.ccalarmclock.model;

import java.io.Serializable;

/**
 * Created by zzf on 16/12/22.
 */
public class Song implements Serializable {
    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
