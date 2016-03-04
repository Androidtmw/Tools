package com.tmw.tools.bean;

import java.io.Serializable;

/**
 * Created by tmw on 2016/2/16.
 */
public class Advertisement implements Serializable {

    private String picurl;
    private String title;

    public Advertisement() {
    }

    public Advertisement(String picurl, String title) {
        this.picurl = picurl;
        this.title = title;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
