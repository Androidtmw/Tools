package com.tmw.tools.bean;

/**
 * Created by tmw on 2016/2/17.
 */
public class HomeDetail {

    private int type;
    private String value;

    public HomeDetail() {
    }

    public HomeDetail(int type, String value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
