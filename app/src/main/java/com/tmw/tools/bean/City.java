package com.tmw.tools.bean;

/**
 * city的实体类
 */
public class City {

    private int cityid;
    private String cityname;
    private int type = 1;//类型，0表示是标签字母，1代表城市

    public City() {

    }

    public City(String cityname,int type) {

        this.cityname = cityname;
        this.type = type;
    }

    public String getCityname() {

        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /**
     * 此方法用来判断当前对象是否和侧边字母标签相等
     * @param label
     * @return
     */
    public boolean equalsLabel(String label){

        if (this.type==0&&this.getCityname().equals(label)){

            return true;

        }else {
            return false;
        }

    }
}
