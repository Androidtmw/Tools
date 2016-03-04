package com.tmw.tools.bean;

/**
 * Created by tmw on 2016/1/25.
 */
public class PublicChannel {

    private  String name;
    private int channelid;
    private String thumb;
    private String ch_name;

    public PublicChannel() {
    }

    public PublicChannel(String name, int channelid, String thumb,String ch_name) {
        this.name = name;
        this.channelid = channelid;
        this.thumb = thumb;
        this.ch_name = ch_name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChannelid() {
        return channelid;
    }

    public void setChannelid(int channelid) {
        this.channelid = channelid;
    }

    public String getCh_name() {
        return ch_name;
    }

    public void setCh_name(String ch_name) {
        this.ch_name = ch_name;
    }
}
