package com.tmw.tools.bean;

/**
 * 首页新闻的实体类
 */
public class HomeNews {

    private String id;
    private int type;
    private String title;
    private String summary;

    private String thumbnail;

    private String groupthumbnail;

    public String getGroupthumbnail() {
        return groupthumbnail;
    }

    public void setGroupthumbnail(String groupthumbnail) {
        this.groupthumbnail = groupthumbnail;
    }

    public HomeNews() {
    }

    public HomeNews(String id, int type, String title, String summary, String thumbnail) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.summary = summary;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
