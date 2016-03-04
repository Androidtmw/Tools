package com.tmw.tools.bean;

/**
 * Created by tmw on 2016/2/20.
 */
public class Song {


    /**
     * songid : 266069
     * title : 不要说话
     * artist : 陈奕迅
     * thumb : http://qukufile2.qianqian.com/data2/pic/115429970/115429970.jpg
     * method : 1024
     * flow : 0
     * artist_id : 87
     * all_artist_id : 87
     * resource_type : 0
     * havehigh : 2
     * charge : 0
     * all_rate : 24,64,128,192,256,320,flac
     */

    private String songid;
    private String title;
    private String artist;
    private String thumb;

    public void setSongid(String songid) {
        this.songid = songid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getSongid() {
        return songid;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getThumb() {
        return thumb;
    }
}
