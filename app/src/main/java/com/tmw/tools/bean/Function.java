package com.tmw.tools.bean;

/**
 * Created by tmw on 2016/2/18.
 */
public class Function {

    private int picture;
    private String funName;

    public Function() {
    }

    public Function(int picture, String funName) {
        this.picture = picture;
        this.funName = funName;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }
}
