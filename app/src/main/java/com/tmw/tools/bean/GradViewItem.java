package com.tmw.tools.bean;

/**
 * Created by tmw on 2016/2/16.
 */
public class GradViewItem {

    private String name;
    private int resId;

    public GradViewItem() {
    }

    public GradViewItem(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
