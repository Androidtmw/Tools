package com.tmw.tools.bean;

/**
 * Created by tmw on 2016/1/25.
 */
public class SingerChannel {

    private String name;
    private int artistid;
    private String avatar;

    public SingerChannel() {
    }

    public SingerChannel(String name, int artistid, String avatar) {
        this.name = name;
        this.artistid = artistid;
        this.avatar = avatar;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtistid() {
        return artistid;
    }

    public void setArtistid(int artistid) {
        this.artistid = artistid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
