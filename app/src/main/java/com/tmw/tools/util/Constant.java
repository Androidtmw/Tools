package com.tmw.tools.util;

/**
 * Created by tmw on 2016/2/15.
 */
public interface Constant {

    interface Url{
        //城市接口
        String CHOICE_CITY = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&act=kftcitylistnew&channel=71&devid=866500021200250&appname=QQHouse&mod=appkft";

        String FIRST_NEWS = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&act=homepage&channel=71&cityid=%s";
        String FIRST_PAGE_LISTVIEW = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&reqnum=%d&pageflag=%d&act=newslist&channel=71&buttonmore=%d&cityid=%s";

        String NEWS_DETAIL = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&act=newsdetail&channel=71&newsid=%s";

        String LOOKING_NEWHOUSE = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&rn=10&order=0&searchtype=normal&devid=866500021200250&page=%d&appname=QQHouse&mod=appkft&act=searchhouse&channel=71&cityid=%s";

        String JOKE_URL = "http://api.1-blog.com/biz/bizserver/xiaohua/list.do?size=10&page=";

        String WEATHER = "http://wthrcdn.etouch.cn/weather_mini?city=";



        String DIANTAI_URL="http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getCategoryList&format=json";

        String SONG_URL = "http://ting.baidu.com/data/music/links?songIds=%d";

        String MUSIC_LIST_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.radio.getChannelSong&format=json&pn=0&rn=10&channelname=%s";

    }

    //请求码
    interface KEY{
        int REQUEST_CODE = 0;
        int RESULT_CODE = 1;
        String CITY_NAME = "cityName";//城市名
        String CITY_ID = "cityID";//城市ID

        String UN = "username";
        String PWD = "password";
    }


}
