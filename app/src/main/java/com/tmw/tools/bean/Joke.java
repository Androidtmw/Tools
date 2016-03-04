package com.tmw.tools.bean;

/**
 * Created by tmw on 2016/2/18.
 */
public class Joke {


    /**
     * id : 41558
     * xhid : 41558
     * author : 宝峰任
     * content : 这样的能过么？希望可以吧！
     * picUrl : http://img.appd.lengxiaohua.cn/2016/02/17/9d5317a779810_o.jpg
     * status : 1
     */

    private int id;
    private int xhid;
    private String author;
    private String content;
    private String picUrl;

    public void setId(int id) {
        this.id = id;
    }

    public void setXhid(int xhid) {
        this.xhid = xhid;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getId() {
        return id;
    }

    public int getXhid() {
        return xhid;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getPicUrl() {
        return picUrl;
    }
}
