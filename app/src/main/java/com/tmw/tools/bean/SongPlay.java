package com.tmw.tools.bean;

/**
 * Created by tmw on 2016/2/20.
 */
public class SongPlay {


    /**
     * queryId : 64563148
     * songId : 64563148
     * songName : 趁早
     * artistId : 68153647
     * artistName : 李琦
     * albumId : 64548663
     * albumName : 中国好声音第二季 第1期
     * songPicSmall : http://c.hiphotos.baidu.com/ting/pic/item/b8389b504fc2d562cc71decce51190ef76c66c91.jpg
     * songPicBig : http://a.hiphotos.baidu.com/ting/pic/item/37d12f2eb9389b500886ee738735e5dde7116e91.jpg
     * songPicRadio : http://a.hiphotos.baidu.com/ting/pic/item/cdbf6c81800a19d8d32c8a2431fa828ba61e46e7.jpg
     * lrcLink : /data2/lrc/64586422/64586422.lrc
     * version :
     * copyType : 1
     * time : 197
     * linkCode : 22000
     * songLink : http://zhangmenshiting.baidu.com/data2/music/134378197/64563148100800.mp3?xcode=a2cd555bdd851054878a5546ba115fb8309abd2b9f5b6d72
     * showLink : http://zhangmenshiting.baidu.com/data2/music/134378197/64563148100800.mp3?xcode=a2cd555bdd851054878a5546ba115fb8309abd2b9f5b6d72
     * format : mp3
     * rate : 128
     * size : 3158428
     * relateStatus : 0
     * resourceType : 0
     */

    private int songId;
    private String songName;
    private String artistId;
    private String artistName;
    private String albumName;
    private String songPicSmall;
    private String songPicBig;
    private String songPicRadio;

    public String getSongPicRadio() {
        return songPicRadio;
    }

    public void setSongPicRadio(String songPicRadio) {
        this.songPicRadio = songPicRadio;
    }

    private String lrcLink;
    private int time;
    private String songLink;
    private int size;

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setSongPicSmall(String songPicSmall) {
        this.songPicSmall = songPicSmall;
    }

    public void setSongPicBig(String songPicBig) {
        this.songPicBig = songPicBig;
    }

    public void setLrcLink(String lrcLink) {
        this.lrcLink = lrcLink;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSongId() {
        return songId;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getSongPicSmall() {
        return songPicSmall;
    }

    public String getSongPicBig() {
        return songPicBig;
    }

    public String getLrcLink() {
        return lrcLink;
    }

    public int getTime() {
        return time;
    }

    public String getSongLink() {
        return songLink;
    }

    public int getSize() {
        return size;
    }
}
